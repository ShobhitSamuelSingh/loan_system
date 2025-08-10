package com.shobhit.loan_system.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "agents")
@Data
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agent_id;

    @Column(nullable = false)
    private  String agent_name;

    @Column(name = "manager_id")
    private Long manager_id;

    @Column(name = "available")
    private boolean available = true;
}
