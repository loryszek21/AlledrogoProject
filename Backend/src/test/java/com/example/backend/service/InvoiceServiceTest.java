package com.example.backend.service;

import com.example.backend.dto.InvoiceDataDTO;
import com.example.backend.model.Order;
import com.example.backend.model.OrderItem;
import com.example.backend.model.Product;
import com.example.backend.model.VatRate;
import com.example.backend.repository.InvoiceRepository;
import com.example.backend.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Ensure Mockito annotations are processed
class InvoiceServiceTest {

    @InjectMocks
    private InvoiceService invoiceService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private InvoiceRepository invoiceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generateInvoice() {
        // Given: Mock Order Repository
        Integer orderId = 1;
        Order mockOrder = new Order();
        mockOrder.setId(orderId);
        mockOrder.setOrderDate(LocalDate.now());

        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(2);
        Product product = new Product();
        product.setProductName("Test Product");
        product.setProduct_price(100.0);
        VatRate vatRate = new VatRate();
        vatRate.setRateValue(23.0);
        product.setVatRate(vatRate);
        orderItem.setProduct(product);

        mockOrder.setOrderItems(Collections.singletonList(orderItem));

        // Mock the orderRepository to return the mock order
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(mockOrder));

        // When: Calling generateInvoice method
        byte[] invoice = invoiceService.generateInvoice(orderId);

        // Then: Validate the result
        assertNotNull(invoice);
        verify(orderRepository, times(1)).findById(orderId);
    }


    @Test
    void saveToDatabase() {
        // Given
        InvoiceDataDTO mockInvoiceData = new InvoiceDataDTO();
        mockInvoiceData.setInvoiceType("invoice");
        mockInvoiceData.setNip("1234567890");
        mockInvoiceData.setCompanyName("Test Company");
        mockInvoiceData.setFirstName("John");
        mockInvoiceData.setLastName("Doe");
        mockInvoiceData.setEmail("john.doe@example.com");
        mockInvoiceData.setPostCode("12345");
        mockInvoiceData.setStreetAddress("123 Test St");
        mockInvoiceData.setCity("Test City");
        mockInvoiceData.setUserId(1);
        mockInvoiceData.setOrderId(1);

        // When
        invoiceService.saveToDatabase(mockInvoiceData, 1);

        // Then
        verify(invoiceRepository, times(1)).save(any());
    }

    // Helper method to mock product (assuming Product is a model class)
    private Product mockProduct() {
        Product product = new Product();
        product.setProductName("Sample Product");
        product.setProduct_price(100.0);  // Set a mock price
        VatRate vatRate = new VatRate();
        vatRate.setRateValue(23.0);  // Set a mock VAT rate
        product.setVatRate(vatRate);  // Link the mock VAT rate
        return product;
    }
}
