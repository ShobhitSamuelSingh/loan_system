package com.shobhit.loan_system.service;


import com.shobhit.loan_system.model.entity.Agent;
import com.shobhit.loan_system.model.entity.Customer;
import com.shobhit.loan_system.model.entity.Loan;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public interface NotificationService {
    void sendPushNotification(Agent agent, String message);
    void sendSMStoCustomer(Loan loan);
}

