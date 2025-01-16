package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")

    private long id;
    @Column(name = "name")
    private String productName;
    @Column(name = "description")
    private String product_description;
    @Column(name = "price")

    private Double product_price;

    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "vat_rate", nullable = false)
//@Column(name = "vat_rate")
    private VatRate vatRate;


    public void setProductPrice(double v) {
    }

    public void setName(String testProduct) {
    }
}