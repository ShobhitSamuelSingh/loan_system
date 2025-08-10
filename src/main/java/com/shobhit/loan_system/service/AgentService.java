package com.shobhit.loan_system.service;

import com.shobhit.loan_system.model.entity.Agent;
import com.shobhit.loan_system.model.entity.Loan;
import com.shobhit.loan_system.repository.AgentRepository;
import com.shobhit.loan_system.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AgentService {


    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private NotificationService notificationService;

    public void saveEntry(Agent newAgent) {
        agentRepository.save(newAgent);
    }

    public List<Agent> getAll() { return agentRepository.findAll(); }

    public void assignAgentToLoan(Loan freshLoan) {
        Optional<Agent> availableAgent = agentRepository.findFirstByAvailableTrue();

        if(availableAgent.isPresent()) {
            Agent agent = availableAgent.get();
            freshLoan.setAgent(agent);
            loanRepository.save(freshLoan);

            agent.setAvailable(false);
            agentRepository.save(agent);
            notificationService.sendPushNotification(agent, "New Loan to be review.");
        }
    }
}
