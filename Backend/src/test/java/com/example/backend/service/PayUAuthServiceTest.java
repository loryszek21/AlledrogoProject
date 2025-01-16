package com.example.backend.service;

import com.example.backend.dto.OrderPayment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PayUAuthServiceTest {

    @InjectMocks
    private PayUAuthService payUAuthService;

    @Mock
    private OrderService orderService;

    @Mock
    private RestTemplate restTemplate; // Mock RestTemplate

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOrder_whenAmountIsNull_returnsError() {
        OrderPayment mockOrderPayment = new OrderPayment();
        mockOrderPayment.setAmount(null); // Set amount to null

        String result = payUAuthService.createOrder(mockOrderPayment);

        assertEquals("Error: amount must not be null", result);
    }
}
