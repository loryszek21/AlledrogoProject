package com.example.backend.controller;

import com.example.backend.service.PayUAuthService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PayUController {

    private final PayUAuthService payUAuthService;

    public PayUController(PayUAuthService payUAuthService) {
        this.payUAuthService = payUAuthService;
    }

    @GetMapping("/payu/auth")
    public String getPayUAuthToken() {
        return payUAuthService.authenticate();
    }


    @PostMapping("/payu/order")
    public String createOrder() {
//        String bearerToken = token.replace("Bearer ", "");

//        return payUAuthService.createOrder();
        return "OK";
//    }

}
}
