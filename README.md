# telegram-bot #

This repository demonstrates how to create an echo bot in Telegram with Spring Framework.

### How do I get set up? ###

* Make your bot with the [BotFather](https://telegram.me/botfather) and you will get token for your bot

* Set your webhook

	`$ curl -F "url=<your_webhook_url>" https://api.telegram.org/bot<token>/setWebhook`

* Make request
	`https://api.telegram.org/bot<token>/METHOD_NAME`

* Get update

    ```java
    @PostMapping(value="/callback")
    public ResponseEntity<String> callback(@RequestBody String aUpdate)
    ```

* Send messages

	```java
	String url = "https://api.telegram.org/bot"+tToken+"/sendMessage";
        
    HttpClient client = HttpClientBuilder.create().build();
    HttpPost post = new HttpPost(url);
        
    post.setHeader("Content-Type", "application/json");
        
    try{
    	StringEntity params = new StringEntity("{\"chat_id\":"+chatId+",\"text\":\""+userText+"\"}");
        System.out.println("Parameter: " + params);
        post.setEntity(params);
        HttpResponse response = client.execute(post);
        System.out.println("Response Code : "+response.getStatusLine().getStatusCode());
            
	    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
                result.append(line);
        }
    } catch(IOException e){
        System.out.println("Exception is raised");
    }
	```

### How do I contribute? ###

* Add your name and e-mail address into CONTRIBUTORS.txt
