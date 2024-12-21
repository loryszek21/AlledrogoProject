package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class CartDTO {
    private Long id;
    private Long productId;
    private String userName;
    private String productName;
    private int quantity;
    private double price;

}
