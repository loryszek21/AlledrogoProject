package com.example.backend.service;

import com.example.backend.dto.OrderPayment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpServerErrorException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class PayUAuthService {
    private static final Logger logger = LoggerFactory.getLogger(PayUAuthService.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private OrderService orderService;

    @Value("${payu.oauth.url}")
    private String oauthUrl;

    @Value("${payu.client.id}")
    private String clientId;

    @Value("${payu.client.secret}")
    private String clientSecret;

    @Value("${payu.order.url}")
    private String orderUrl;


    public PayUAuthService(OrderService orderService) {
        this.orderService = orderService;
    }

    public String authenticate() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

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

    public String createOrder(OrderPayment orderPayment) {
//        RestTemplate restTemplate = new RestTemplate();
        String accessToken = authenticate();
        System.out.println("Access Token: " + accessToken);
//        System.out.println(accessToken);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + accessToken);

        System.out.println(orderPayment.amount);
        Map<String, Object> requestBodyMap = Map.of(


                "customerIp", "127.0.0.1",
                "merchantPosId", "486884",
                "description", "RTV market",
                "currencyCode", orderPayment.currency,
                "totalAmount", orderPayment.getAmount().replace(".", ""),
                "products", orderPayment.getCartDTO().stream().map(cart -> Map.of(
                                "name", cart.getProductName(),
                                "unitPrice", String.valueOf(
                                        BigDecimal.valueOf(cart.getPrice()).multiply(BigDecimal.valueOf(100)).intValue()),
                                "quantity", cart.getQuantity()
                        ))
                        .toList()

        );
        String requestBody;
        try {
            // Serializuj mapę do JSON
            requestBody = objectMapper.writeValueAsString(requestBodyMap);
        } catch (Exception e) {
            logger.error("Error serializing request body", e);
            throw new RuntimeException("Error creating request body JSON");
        }

        System.out.println("Headers:");
        headers.forEach((key, value) -> System.out.println(key + ": " + value));
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();

        try {
            // Log the request body and headers
            logger.info("Request Body: " + requestBody);
            logger.info("Request Headers: " + headers);

            // Wykonanie żądania POST
            ResponseEntity<String> response = restTemplate.exchange(orderUrl, HttpMethod.POST, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK || response.getStatusCode() == HttpStatus.FOUND) {
                Map body;
                try {
                    body = objectMapper.readValue(response.getBody(), Map.class);
                } catch (Exception e) {
                    logger.error("Error deserializing response body", e);
                    throw new RuntimeException("Error parsing response JSON", e);
                }
                orderService.saveOrder(orderPayment);

                return (String) body.get("redirectUri");
            } else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                logger.error("Unauthorized response: " + response.getBody());
            } else if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
                logger.error("Internal Server Error response: " + response.getBody());
            } else {
                logger.error("Something went wrong: " + response.getBody());
            }

            // Zwrócenie odpowiedzi jako String
            return response.getBody();
        } catch (HttpServerErrorException e) {
            logger.error("Server error: " + e.getResponseBodyAsString(), e);
            return "Error: " + e.getMessage();
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage(), e);
            return "Error: " + e.getMessage();
        }
    }
}
