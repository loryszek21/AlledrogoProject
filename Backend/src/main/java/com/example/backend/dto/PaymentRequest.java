package com.example.backend.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private OrderPayment orderPayment;
    private InvoiceDataDTO invoiceDataDTO;

}
