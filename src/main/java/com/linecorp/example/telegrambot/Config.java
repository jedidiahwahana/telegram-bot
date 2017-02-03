
package com.linecorp.example.telegrambot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.properties")
public class Config
{
    @Autowired
    Environment mEnv;
    
    @Bean(name="com.telegram.token")
    public String getTelegramToken()
    {
        return mEnv.getProperty("com.telegram.token");
    }
};
