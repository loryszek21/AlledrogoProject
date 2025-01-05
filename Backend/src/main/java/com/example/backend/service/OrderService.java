package com.example.backend.service;

import com.example.backend.dto.CartDTO;
import com.example.backend.dto.OrderPayment;
import com.example.backend.model.CartItem;
import com.example.backend.model.Order;
import com.example.backend.model.OrderItem;
import com.example.backend.model.User;
import com.example.backend.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class OrderService {

private final OrderRepository orderRepository;
private final UserRepository userRepository;
private final CartItemRepository cartItemRepository;
private final OrderItemRepository orderItemRepository;


    public OrderService(OrderRepository orderRepository, UserRepository userRepository, CartItemRepository cartItemRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public void saveOrder(OrderPayment orderPayment) {
        User user = userRepository.findByUsername(orderPayment.getUserName()).orElseThrow(()-> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus("COMPLETED");
        Order orderSaved = orderRepository.save(order);


        for (CartDTO cartDTO : orderPayment.getCartDTO()){
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(orderSaved);
            orderItem.setQuantity(cartDTO.getQuantity());
//            System.out.println(cartItemRepository.findProductById(cartDTO.getProductId()));
            orderItem.setProduct(cartItemRepository.findProductById(cartDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found")));
            orderItemRepository.save(orderItem);

        }
        cartItemRepository.deleteAllByUserId(Long.valueOf(user.getId()));

    }
}
