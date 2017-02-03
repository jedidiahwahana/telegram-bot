
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

@RestController
@RequestMapping(value="/telegrambot")
public class BotController
{
    private final String tToken = "277622307:AAHumppdwKqfWxqgXzddyUukeTrF_zCNkWs";
    
    @GetMapping(value="/callback")
    public ResponseEntity<String> callback()
    {
        System.out.println("Webhook received");
        
        String url = "https://api.telegram.org/bot"+tToken+"/getupdates";
        
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        
        try{
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
            System.out.println("Result: " + result);
        } catch(IOException e) {
            System.out.println("Exception raised");
        }
        
        return new ResponseEntity<String>("Web is running", HttpStatus.OK);
    }
};
