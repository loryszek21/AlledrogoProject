package com.example.backend.service;

import com.example.backend.dto.CartDTO;
import com.example.backend.dto.OrderPayment;
import com.example.backend.model.Order;
import com.example.backend.model.OrderItem;
import com.example.backend.model.User;
import com.example.backend.repository.OrderItemRepository;
import com.example.backend.repository.OrderRepository;
import com.example.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Ensure Mockito annotations are processed
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveOrder() {
        String userName = "testUser";
        User mockUser = new User();
        mockUser.setId(1);
        mockUser.setUsername(userName);

        // Create mock CartDTO
        CartDTO mockCart = new CartDTO();
        mockCart.setProductId(1L);
        mockCart.setQuantity(2);

        OrderPayment mockOrderPayment = new OrderPayment();
        mockOrderPayment.setUserName(userName);
        mockOrderPayment.setCartDTO(Collections.singletonList(mockCart));

        // Mock user repository behavior
        when(userRepository.findByUsername(userName)).thenReturn(Optional.of(mockUser));

        // Mock order save behavior
        Order mockOrder = new Order();
        mockOrder.setId(1);
        mockOrder.setOrderDate(LocalDate.now());
        mockOrder.setUser(mockUser);

        // Mock order item save behavior
        OrderItem mockOrderItem = new OrderItem();
        mockOrderItem.setId(mockCart.getProductId());
        mockOrderItem.setQuantity(mockCart.getQuantity());

        when(orderRepository.save(any(Order.class))).thenReturn(mockOrder);
        when(orderItemRepository.save(any(OrderItem.class))).thenReturn(mockOrderItem);

        // Call the saveOrder method and assert
        Order savedOrder = orderService.saveOrder(mockOrderPayment);

        assertNotNull(savedOrder);
        assertEquals(mockOrder.getId(), savedOrder.getId());
        assertEquals(mockOrder.getOrderDate(), savedOrder.getOrderDate());
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(orderItemRepository, times(1)).save(any(OrderItem.class));
    }


    @Test
    void getOrdersPayment() {
        String userName = "testUser";
        User mockUser = new User();
        mockUser.setId(1);
        mockUser.setUsername(userName);

        // Create a mock Order
        Order mockOrder = new Order();
        mockOrder.setId(1);
        mockOrder.setOrderDate(LocalDate.now());
        mockOrder.setOrderStatus("COMPLETED");

        // Mocking repository behavior
        when(userRepository.findByUsername(userName)).thenReturn(Optional.of(mockUser)); // Ensure this line is correct
        when(orderRepository.findByUserId(mockUser.getId())).thenReturn(Collections.singletonList(mockOrder));

        // Call the getOrdersPayment method
        List<OrderPayment> orders = orderService.getOrdersPayment(userName);

        // Assertions
        assertNotNull(orders);
        assertEquals(1, orders.size());
        assertEquals(mockOrder.getId(), orders.get(0).getOrderId());
        verify(orderRepository, times(1)).findByUserId(mockUser.getId());
    }
}
