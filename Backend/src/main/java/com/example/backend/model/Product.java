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
    private String product_name;
    @Column(name = "description")
    private String product_description;
    @Column(name = "price")

    private Double product_price;

    @ManyToOne
    @JoinColumn(name="category", nullable = false)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "vat_rate", nullable = false)
//@Column(name = "vat_rate")
    private VatRate vatRate;


//    public Long getProduct_id() {
//        return product_id;
//    }
//
//    public void setProduct_id(Long product_id) {
//        this.product_id = product_id;
//    }

//    public String getProduct_name() {
//        return product_name;
//    }
//
//    public void setProduct_name(String product_name) {
//        this.product_name = product_name;
//    }
//
//    public String getProduct_description() {
//        return product_description;
//    }
//
//    public void setProduct_description(String product_description) {
//        this.product_description = product_description;
//    }
//
//    public Double getProduct_price() {
//        return product_price;
//    }
//
//    public void setProduct_price(Double product_price) {
//        this.product_price = product_price;
//    }
//
//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//
//    public VatRate getVatRate() {
//        return vatRate;
//    }
//
//    public void setVatRate(VatRate vatRate) {
//        this.vatRate = vatRate;
//    }
}
