package com.example.backend.model;

import jakarta.persistence.*;
        import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "invoicedata")
public class InvoicedataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('invoicedata_invoice_id_seq'")
    @Column(name = "invoice_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "nip")
    private String nip;

    @Size(max = 255)
    @Column(name = "company_name")
    private String companyName;

    @Size(max = 255)
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 255)
    @Column(name = "last_name")
    private String lastName;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @Size(max = 10)
    @Column(name = "post_code", length = 10)
    private String postCode;

    @Size(max = 255)
    @Column(name = "street_address")
    private String streetAddress;

    @Size(max = 10)
    @Column(name = "apartment_number", length = 10)
    private String apartmentNumber;

    @Size(max = 255)
    @Column(name = "city")
    private String city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

}