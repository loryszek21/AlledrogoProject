package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "VatRates")
public class VatRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vat_rate_id")
    private long idVat;
    @Column(name = "rate_name")
    String rateName;
    @Column(name = "rate_value")
    Double rateValue;


}
