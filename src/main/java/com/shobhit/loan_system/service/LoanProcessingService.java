package com.shobhit.loan_system.service;

import com.shobhit.loan_system.model.entity.Loan;
import com.shobhit.loan_system.model.enums.LoanStatus;
import com.shobhit.loan_system.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class LoanProcessingService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private AgentService agentService;

    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    @Scheduled(fixedDelay = 10000)
    public void processLoans() {
        List<Loan> appliedLoans = loanRepository.findTop10ByLoanStatus(LoanStatus.APPLIED);
        for(Loan loan : appliedLoans) {
            executor.submit(() -> processLoan(loan));
        }
    }

    private void processLoan(Loan loan) {
            Loan freshLoan = loanRepository.findById(loan.getLoan_id()).orElse(null);

            //Double check
            if(freshLoan == null || freshLoan.getLoanStatus() != LoanStatus.APPLIED){
                return;
            }

            //system checks for 25s
//            try { Thread.sleep(25000); }
//            catch(InterruptedException e) { Thread.currentThread().interrupt(); }

            if( conditionForSystemRejection(freshLoan) == true ) {
                freshLoan.setLoanStatus(LoanStatus.REJECTED_BY_SYSTEM);
            }
            else if( conditionForSystemApproval(freshLoan)  == true ) {
                freshLoan.setLoanStatus(LoanStatus.APPROVED_BY_SYSTEM);
            }
            else {
                freshLoan.setLoanStatus(LoanStatus.UNDER_REVIEW);
                agentService.assignAgentToLoan(freshLoan);
            }
            loanRepository.save(freshLoan);
    }

    private boolean conditionForSystemRejection(Loan freshLoan) {
        Double MAX_Amount = 100000.0;
        return freshLoan.getLoanAmount() > MAX_Amount;
    }

    private boolean conditionForSystemApproval(Loan freshLoan) {
        Double MIN_Amount = 50000.0;
        return freshLoan.getLoanAmount() <= MIN_Amount;
    }
}
