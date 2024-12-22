package com.example.backend.controller;

import com.example.backend.service.PayUAuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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




}
