package com.henrique.guribot;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootApplication
@Log4j2
public class GuribotApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuribotApplication.class, args);
        run();
    }
    public static void run() {
        try {
            String requestBody = """
                {
                    "messaging_product": "whatsapp",
                    "recipient_type": "individual",
                    "to": "xxxxxxxxxxxxx",
                    "type": "template",
                    "template": {
                        "name": "hello_world",
                        "language": {
                            "code": "en_US"
                        }
                    }
                }
                """;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://graph.facebook.com/v13.0/xx/messages"))
                    .header("Authorization", "Bearer xx")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpClient http = HttpClient.newHttpClient();
            HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

        } catch (URISyntaxException | IOException | InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}
