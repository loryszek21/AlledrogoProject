package com.example.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class PayUAuthService {

    @Value("${payu.oauth.url}")
    private String oauthUrl;

    @Value("${payu.client.id}")
    private String clientId;

    @Value("${payu.client.secret}")
    private String clientSecret;

    public String authenticate() {
        RestTemplate restTemplate = new RestTemplate();

        // Tworzenie nagłówków
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Tworzenie ciała żądania
        String requestBody = "grant_type=client_credentials"
                + "&client_id=" + clientId
                + "&client_secret=" + clientSecret;

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        // Wykonanie POST
        ResponseEntity<Map> response = restTemplate.exchange(
                oauthUrl,
                HttpMethod.POST,
                request,
                Map.class
        );

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return (String) response.getBody().get("access_token");
        } else {
            throw new RuntimeException("Błąd podczas autoryzacji PayU: " + response.getStatusCode());
        }
    }
}