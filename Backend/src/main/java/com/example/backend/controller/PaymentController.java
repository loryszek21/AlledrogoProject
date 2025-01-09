package com.example.backend.controller;

import com.example.backend.dto.PaymentRequest;
import com.example.backend.service.InvoiceService;
import com.example.backend.service.PayUAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {
    private final PayUAuthService payUAuthService;
    private final InvoiceService invoiceService;

    public PaymentController(PayUAuthService payUAuthService, InvoiceService invoiceService) {
        this.payUAuthService = payUAuthService;
        this.invoiceService = invoiceService;
    }

    @PostMapping("/payment")
    public ResponseEntity<String> getPayUAuthToken(@RequestBody PaymentRequest paymentRequest) {
        String redirectUrl = payUAuthService.createOrder(paymentRequest.getOrderPayment());
        Integer orderId = paymentRequest.getOrderPayment().getOrderId();
        invoiceService.saveToDatabase(paymentRequest.getInvoiceDataDTO(), orderId);
        return ResponseEntity.ok(redirectUrl);
    }
}
