package com.shobhit.loan_system.service;

import com.shobhit.loan_system.model.entity.Agent;
import com.shobhit.loan_system.model.entity.Customer;
import com.shobhit.loan_system.model.entity.Loan;
import com.shobhit.loan_system.repository.AgentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class MockNotificationService implements NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(MockNotificationService.class);

    @Autowired
    private AgentRepository agentRepository;

    @Override
    public void sendPushNotification(Agent agent, String message) {
        logger.info("Push notification to Agent {}: {}", agent.getAgent_name(), message);
        logger.info("Push notification to Manager {}: {} to Agent: {}", agentRepository.findById(agent.getManager_id())  , message, agent.getAgent_name());
    }

    @Override
    public void sendSMStoCustomer(Loan loan) {
        logger.info("Send SMS to Customer {}: Loan Id. {} Status {}", loan.getCustomer().getCust_name(), loan.getLoan_id(),loan.getLoanStatus());
    }
}
