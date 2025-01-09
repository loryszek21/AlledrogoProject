package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceDataDTO {
    private String invoiceType;
    private String nip;
    private String companyName;
    private String firstName;
    private String lastName;
    private String email;
    private String postCode;
    private String streetAddress;
    private String apartmentNumber;
    private String city;
    private Integer userId;
    private Integer orderId;
}