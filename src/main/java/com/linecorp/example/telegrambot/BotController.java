
package com.linecorp.example.telegrambot;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.HttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import java.util.*;
import org.json.JSONObject;

@RestController
@RequestMapping(value="/telegrambot")
public class BotController
{
    private final String tToken = "277622307:AAHumppdwKqfWxqgXzddyUukeTrF_zCNkWs";
    
    @PostMapping(value="/callback")
    public ResponseEntity<String> callback(@RequestBody String aUpdate)
    {
        System.out.println("Webhook received");
        
        System.out.println("Update: " + aUpdate);
        
        JSONObject jUpdate = new JSONObject(aUpdate);
        JSONObject jMessage = jUpdate.getJSONObject("message");
        System.out.println("Message: " + jMessage);
        String userText = jMessage.getString("text");
        JSONObject jChat = jMessage.getJSONObject("chat");
        int chatId = jChat.getInt("id");
        
        String url = "https://api.telegram.org/"+tToken+"/sendMessage";
        
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("chat_id", chatId));
        urlParameters.add(new BasicNameValuePair("text", userText));
        
        try{
            post.setEntity(new UrlEncodedFormEntity(urlParameters));
            HttpResponse response = client.execute(post);
            System.out.println("Response Code : "
                               + response.getStatusLine().getStatusCode());
            
            BufferedReader rd = new BufferedReader(
                                                   new InputStreamReader(response.getEntity().getContent()));
            
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        } catch(IOException e){
            System.out.println("Exception is raised");
        }
        
        return new ResponseEntity<String>("Web is running", HttpStatus.OK);
    }
};
