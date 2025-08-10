package com.shobhit.loan_system.service;

import com.shobhit.loan_system.model.entity.Agent;
import com.shobhit.loan_system.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    public void saveEntry(Agent newAgent) {
        agentRepository.save(newAgent);
    }

    public List<Agent> getAll() { return agentRepository.findAll(); }
}
