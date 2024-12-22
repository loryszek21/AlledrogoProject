package com.example.backend.controller;

import com.example.backend.dto.CartDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {
    @PostMapping("/payment")
    public ResponseEntity <String> getPayUAuthToken() {
        return ResponseEntity.ok("OK");
    }
}
