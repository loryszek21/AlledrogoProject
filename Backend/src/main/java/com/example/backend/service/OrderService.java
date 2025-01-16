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
import java.util.List;
import java.util.stream.Collectors;

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
    public Order saveOrder(OrderPayment orderPayment) {
        try {
            User user = userRepository.findByUsername(orderPayment.getUserName())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Order order = new Order();
            order.setUser(user);
            order.setOrderDate(LocalDate.now());
            order.setOrderStatus("COMPLETED");
            Order orderSaved = orderRepository.save(order);

            for (CartDTO cartDTO : orderPayment.getCartDTO()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(orderSaved);
                orderItem.setQuantity(cartDTO.getQuantity());
                orderItem.setProduct(cartItemRepository.findProductById(cartDTO.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found")));
                orderItemRepository.save(orderItem);
            }
            cartItemRepository.deleteAllByUserId(Long.valueOf(user.getId()));
            return orderSaved;

        } catch (RuntimeException e) {
            throw e; // Preserve original exception message
        } catch (Exception e) {
            throw new RuntimeException("Error while saving order", e);
        }
    }


    public List<OrderPayment> getOrdersPayment(String userName) {
        User user = userRepository.findByUsername(userName).orElseThrow(()-> new RuntimeException("User not found"));
        
//        OrderPayment orderPayment = new OrderPayment();
List<Order> orders = orderRepository.findByUserId(user.getId());

return orders.stream().map(order -> {
    OrderPayment orderPayment = new OrderPayment();
    orderPayment.setOrderId(order.getId());
    orderPayment.setUserName(user.getUsername());
    orderPayment.setOrderDate(order.getOrderDate());
    orderPayment.setOrderStatus(order.getOrderStatus());
    orderPayment.setCartDTO(
            order.getOrderItems().stream().map(orderItem -> {
                CartDTO cartDTO = new CartDTO();
                cartDTO.setProductId(orderItem.getProduct().getId());
                cartDTO.setQuantity(orderItem.getQuantity());
                cartDTO.setProductName(orderItem.getProduct().getProductName());
                cartDTO.setPrice(orderItem.getProduct().getProduct_price());
                return cartDTO;
            }).collect(Collectors.toList())
    );
    return orderPayment;
}).collect(Collectors.toList());


    }
}
