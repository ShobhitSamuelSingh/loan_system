package com.shobhit.loan_system.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cust_id;

    @Column(nullable = false)
    private String cust_name;

    @Column(nullable = false, unique = true)
    private String cust_phone;
}
