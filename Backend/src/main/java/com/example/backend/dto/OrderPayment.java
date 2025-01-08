package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Setter
@Getter

public class OrderPayment {
    public Integer orderId;
    public String amount;
    public String currency;
    public LocalDate orderDate;
    public String orderStatus;
    public String description;
    public List<CartDTO> cartDTO;
    private String userName;


}

