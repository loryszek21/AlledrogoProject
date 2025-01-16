package com.example.backend.service;

import com.example.backend.dto.CartDTO;
import com.example.backend.dto.OrderPayment;
import com.example.backend.model.CartItem;
import com.example.backend.model.Order;
import com.example.backend.model.OrderItem;
import com.example.backend.model.Product;
import com.example.backend.model.User;
import com.example.backend.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private OrderService orderService;

    private User mockUser;
    private Product mockProduct;
    private Order mockOrder;
    private CartDTO mockCartDTO;
    private OrderPayment mockOrderPayment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize mock user
        mockUser = new User();
        mockUser.setId(1);
        mockUser.setUsername("testUser");

        // Initialize mock product
        mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setProductName("Test Product");
        mockProduct.setProduct_price(100.0);

        // Initialize mock order
        mockOrder = new Order();
        mockOrder.setId(1);
        mockOrder.setUser(mockUser);
        mockOrder.setOrderDate(LocalDate.now());
        mockOrder.setOrderStatus("COMPLETED");

        // Initialize mock CartDTO
        mockCartDTO = new CartDTO();
        mockCartDTO.setProductId(mockProduct.getId());
        mockCartDTO.setQuantity(2);
        mockCartDTO.setProductName(mockProduct.getProductName());
        mockCartDTO.setPrice(mockProduct.getProduct_price());

        // Initialize mock OrderPayment
        mockOrderPayment = new OrderPayment();
        mockOrderPayment.setUserName(mockUser.getUsername());
        mockOrderPayment.setCartDTO(Collections.singletonList(mockCartDTO));
    }

    @Test
    void saveOrder_Success() {
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(mockUser));
        when(orderRepository.save(any(Order.class))).thenReturn(mockOrder);
        when(cartItemRepository.findProductById(1L)).thenReturn(Optional.of(mockProduct));

        Order result = orderService.saveOrder(mockOrderPayment);

        assertNotNull(result);
        assertEquals("COMPLETED", result.getOrderStatus());
        assertEquals(mockUser, result.getUser());

        verify(userRepository, times(1)).findByUsername("testUser");
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(cartItemRepository, times(1)).deleteAllByUserId(1L);
    }
    @Test
    void saveOrder_UserNotFound() {
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            orderService.saveOrder(mockOrderPayment);
        });

        assertEquals("User not found", exception.getMessage());
        verify(userRepository, times(1)).findByUsername("testUser");
        verify(orderRepository, never()).save(any(Order.class));
    }
    @Test
    void saveOrder_ProductNotFound() {
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(mockUser));
        when(orderRepository.save(any(Order.class))).thenReturn(mockOrder);
        when(cartItemRepository.findProductById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            orderService.saveOrder(mockOrderPayment);
        });

        assertEquals("Product not found", exception.getMessage());
        verify(userRepository, times(1)).findByUsername("testUser");
        verify(cartItemRepository, times(1)).findProductById(1L);
    }

    @Test
    void getOrdersPayment_Success() {
        mockOrder.setOrderItems(Collections.singletonList(new OrderItem(1L, mockOrder, mockProduct, 2)));

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(mockUser));
        when(orderRepository.findByUserId(1)).thenReturn(Collections.singletonList(mockOrder));

        List<OrderPayment> result = orderService.getOrdersPayment("testUser");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("testUser", result.get(0).getUserName());
        assertEquals(1, result.get(0).getCartDTO().size());
        assertEquals("Test Product", result.get(0).getCartDTO().get(0).getProductName());

        verify(userRepository, times(1)).findByUsername("testUser");
        verify(orderRepository, times(1)).findByUserId(1);
    }

    @Test
    void getOrdersPayment_UserNotFound() {
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            orderService.getOrdersPayment("testUser");
        });

        assertEquals("User not found", exception.getMessage());
        verify(userRepository, times(1)).findByUsername("testUser");
        verify(orderRepository, never()).findByUserId((int) anyLong());
    }
}
