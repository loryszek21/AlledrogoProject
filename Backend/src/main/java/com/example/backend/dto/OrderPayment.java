package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter

public class OrderPayment {
    public String amount;
    public String currency;
    public String description;
    public List<CartDTO> cartDTO;
    private String userName;


}

