package com.example.backend.controller;

import com.example.backend.dto.CartDTO;
import com.example.backend.dto.OrderPayment;
import com.example.backend.service.PayUAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {
    private final PayUAuthService payUAuthService;

    public PaymentController(PayUAuthService payUAuthService) {
        this.payUAuthService = payUAuthService;
    }

    @PostMapping("/payment")
    public ResponseEntity<String> getPayUAuthToken(@RequestBody OrderPayment orderPayment) {
//        System.out.println("Cart DTO product Name"+ orderPayment.cartDTO.getProductName());
        String redirectUrl = payUAuthService.createOrder(orderPayment);

        return ResponseEntity.ok(redirectUrl);
    }
}
