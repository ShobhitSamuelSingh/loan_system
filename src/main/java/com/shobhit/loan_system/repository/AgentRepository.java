package com.shobhit.loan_system.repository;

import com.shobhit.loan_system.model.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent, Long> {

    Optional<Agent> findFirstByAvailableTrue();
}
