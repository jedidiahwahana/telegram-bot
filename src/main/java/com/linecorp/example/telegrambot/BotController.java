
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
    
    @PostMapping(value="/callback")
    public ResponseEntity<String> callback(@RequestBody String aUpdate)
    {
        System.out.println("Webhook received");
        
        System.out.println("Update: " + aUpdate)
        
        return new ResponseEntity<String>("Web is running", HttpStatus.OK);
    }
};
