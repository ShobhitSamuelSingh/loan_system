package com.shobhit.loan_system.config;

import com.shobhit.loan_system.model.entity.Agent;
import com.shobhit.loan_system.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AdminAgentInitializer implements CommandLineRunner {

    @Autowired
    private AgentRepository agentRepository;

    @Override
    @Transactional
    public void run(String... args) {
        if (agentRepository.count() == 0) {
            Agent admin = new Agent();
            admin.setAgent_name("ADMIN");
            admin.set_Available(true);
            admin.setManager_id(null);
            Agent savedAdmin = agentRepository.save(admin);
            savedAdmin.setManager_id(savedAdmin.getAgent_id());
            agentRepository.save(savedAdmin);
        }
    }
}