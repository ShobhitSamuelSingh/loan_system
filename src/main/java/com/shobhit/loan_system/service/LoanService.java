package com.shobhit.loan_system.service;

import com.shobhit.loan_system.dto.LoanRequest;
import com.shobhit.loan_system.model.entity.Customer;
import com.shobhit.loan_system.model.entity.Loan;
import com.shobhit.loan_system.model.enums.LoanStatus;
import com.shobhit.loan_system.repository.AgentRepository;
import com.shobhit.loan_system.repository.CustomerRepository;
import com.shobhit.loan_system.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private NotificationService notificationService;

    public void applyLoan(LoanRequest loanRequest) {
        Loan loan = new Loan();

        // loan.setLoanStatus(LoanStatus.APPLIED); // Status set to applied in constructor;

        Customer customer = customerRepository.findById(loanRequest.getCustomerId())
                .orElseThrow( () -> new RuntimeException("Customer not found"));
        loan.setCustomer(customer);

        loan.setLoanAmount(loanRequest.getLoanAmount());
        loan.setLoanType(loanRequest.getLoanType());
        loanRepository.save(loan);
    }

    public List<Loan> getAll() { return loanRepository.findAll(); }

    public void setDecision(Long loanId, LoanStatus decision) {
        Optional<Loan> loanOpt = loanRepository.findById(loanId);
        if (loanOpt.isPresent()) {
            Loan reviewedLoan = loanOpt.get();
            reviewedLoan.setLoanStatus(decision);
            loanRepository.save(reviewedLoan);
            notificationService.sendSMStoCustomer(reviewedLoan);

            reviewedLoan.getAgent().setAvailable(true);
            agentRepository.save(reviewedLoan.getAgent());
        }
    }

    public Map<String, Long> getLoanStatusCounts() {
        List<Object[]> results = loanRepository.countLoansByStatus();
        Map<String, Long> statusCounts = new HashMap<>();

        for(Object[] row : results) {
            LoanStatus status = (LoanStatus) row[0];
            Long count = (Long) row[1];
            statusCounts.put(status.name(), count);
        }

        for(LoanStatus status : LoanStatus.values()) {
            statusCounts.putIfAbsent(status.name(), 0L);
        }
        return statusCounts;
    }

    public Page<Loan> getLoansByStatus(LoanStatus status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return loanRepository.findByLoanStatus(status, pageable);
    }
}
