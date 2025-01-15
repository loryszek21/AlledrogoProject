package com.example.backend.service;

import com.example.backend.dto.InvoiceDataDTO;
import com.example.backend.mapper.InvoiceMapper;
import com.example.backend.model.*;
import com.example.backend.repository.InvoiceRepository;
import com.example.backend.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceService invoiceService;

    @Test
    void generateInvoice_orderNotFound() {
        // Mock behavior
        when(orderRepository.findById(1)).thenReturn(Optional.empty());

        // Execute and assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> invoiceService.generateInvoice(1)
        );

        assertEquals("Order not found", exception.getMessage());
        verify(orderRepository, times(1)).findById(1);
    }

    @Test
    void saveToDatabase_success() {
        try (MockedStatic<InvoiceMapper> mockedMapper = mockStatic(InvoiceMapper.class)) {
            // Mock inputs and behavior
            InvoiceDataDTO invoiceData = new InvoiceDataDTO();
            invoiceData.setInvoiceType("invoice");
            invoiceData.setNip("1234567890");
            invoiceData.setCompanyName("Test Company");
            invoiceData.setOrderId(1);

            InvoicedataModel mockEntity = new InvoicedataModel();
            mockedMapper.when(() -> InvoiceMapper.toEntity(invoiceData)).thenReturn(mockEntity);

            // Execute
            invoiceService.saveToDatabase(invoiceData, 1);

            // Verify repository interaction
            verify(invoiceRepository, times(1)).save(mockEntity);
        }
    }
}
