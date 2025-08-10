package com.shobhit.loan_system.repository;

import com.shobhit.loan_system.model.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long> {
}
