package com.shobhit.loan_system.model.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String cust_id;

    @Column(nullable = false)
    private String cust_name;

    @Column(nullable = false, unique = true)
    private String cust_phone;
}
