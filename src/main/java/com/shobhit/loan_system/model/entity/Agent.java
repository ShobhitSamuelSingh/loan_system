package com.shobhit.loan_system.model.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "agents")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String agent_id;

    @Column(nullable = false)
    private  String agent_name;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Agent manager;

    @Column(name = "is_available")
    private boolean is_Available;
}
