package com.shobhit.loan_system.service;

import com.shobhit.loan_system.dto.LoanRequest;
import com.shobhit.loan_system.model.entity.Agent;
import com.shobhit.loan_system.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public void applyLoan(LoanRequest loanRequest) {
    }

}
