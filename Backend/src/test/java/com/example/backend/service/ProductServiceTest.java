package com.example.backend.service;

import com.example.backend.model.Product;
import com.example.backend.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Ensure Mockito annotations are processed
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        // Given
        Product product1 = new Product();
        Product product2 = new Product();
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        // When
        List<Product> products = productService.findAll();

        // Then
        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        // Given
        Product product = new Product();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // When
        Product result = productService.findById(1L);

        // Then
        assertNotNull(result);
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void findAllWithPaginationAndSorting() {
        // Given
        Product product1 = new Product();
        Product product2 = new Product();
        Page<Product> page = new PageImpl<>(Arrays.asList(product1, product2));

        when(productRepository.findAll(any(PageRequest.class))).thenReturn(page);

        // When
        Page<Product> result = productService.findAllWithPaginationAndSorting(0, 2, "id", "asc");

        // Then
        assertEquals(2, result.getTotalElements());
        verify(productRepository, times(1)).findAll(any(PageRequest.class));
    }
}
