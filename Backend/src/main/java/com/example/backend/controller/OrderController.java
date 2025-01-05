package com.example.backend.controller;

import com.example.backend.dto.CartDTO;
import com.example.backend.dto.OrderPayment;
import com.example.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/orders/{user_name}")
    public ResponseEntity<List<OrderPayment>> getCart(@PathVariable String user_name) {
        List<OrderPayment> orderPayments = orderService.getOrdersPayment(user_name);
        return ResponseEntity.ok(orderPayments);
    }

}
