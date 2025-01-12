package com.example.backend.service;

import com.example.backend.dto.CartDTO;
import com.example.backend.model.CartItem;
import com.example.backend.model.Product;
import com.example.backend.model.User;
import com.example.backend.repository.CartRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Ensure Mockito annotations are processed
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCartItems() {
        // Given
        String userName = "testuser";
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem = new CartItem();
        Product product = new Product();
        product.setId(1L);
        product.setProductName("Test Product");
        product.setProduct_price(100.0);
        cartItem.setProduct(product);
        cartItem.setQuantity(2);
        cartItems.add(cartItem);

        when(cartRepository.findByUser_username(userName)).thenReturn(cartItems);

        // When
        List<CartDTO> result = cartService.getCartItems(userName);

        // Then
        assertEquals(1, result.size());
        assertEquals("Test Product", result.get(0).getProductName());
        verify(cartRepository, times(1)).findByUser_username(userName);
    }

    @Test
    void addProductToCart_NewProduct() {
        // Given
        CartDTO cartDTO = new CartDTO();
        cartDTO.setUserName("testuser");
        cartDTO.setProductId(1L);
        cartDTO.setQuantity(3);

        User user = new User();
        user.setId(1);

        Product product = new Product();
        product.setId(1L);

        // Stubbing necessary methods
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(productRepository.findProductById(1L)).thenReturn(Optional.of(product));

        // When
        cartService.addProductToCart(cartDTO);

        // Then
        verify(cartRepository, times(1)).save(any(CartItem.class));
    }

    @Test
    void removeProductById_ExistingProduct() {
        // Given
        String userName = "testuser";
        Long productId = 1L;

        User user = new User();
        user.setId(1); // Ensure this matches the expected type (Long)

        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(new Product()); // Initialize the product if necessary

        // Stubbing repository methods
        when(userRepository.findByUsername(userName)).thenReturn(Optional.of(user));
        when(cartRepository.findByUser_IdAndProduct_Id(1L, productId)).thenReturn(cartItem);

        // When
        cartService.removeProductById(productId, userName);

        // Then
        verify(cartRepository, times(1)).delete(cartItem);
        verify(userRepository, times(1)).findByUsername(userName);
        verify(cartRepository, times(1)).findByUser_IdAndProduct_Id(1L, productId);
    }

}