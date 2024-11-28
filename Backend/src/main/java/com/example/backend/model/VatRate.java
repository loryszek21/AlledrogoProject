package com.example.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "VatRates")
public class VatRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vat_rate_id")
    private int idVat;
    @Column(name = "rate_name")
    String rateName;
    @Column(name = "rate_value")
    Double rateValue;

    public int getIdVat() {
        return idVat;
    }

    public void setIdVat(int idVat) {
        this.idVat = idVat;
    }

    public String getRateName() {
        return rateName;
    }

    public void setRateName(String rateName) {
        this.rateName = rateName;
    }

    public Double getRateValue() {
        return rateValue;
    }

    public void setRateValue(Double rateValue) {
        this.rateValue = rateValue;
    }
}
