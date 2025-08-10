package com.shobhit.loan_system.controller;


import com.shobhit.loan_system.dto.LoanRequest;
import com.shobhit.loan_system.model.entity.Loan;
import com.shobhit.loan_system.model.enums.LoanStatus;
import com.shobhit.loan_system.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/v1/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public void loanApplication(@RequestBody LoanRequest loanReq ) {
        loanService.applyLoan(loanReq);
    }

    @GetMapping("/getAll")
    public List<Loan> getAll() { return loanService.getAll(); }

    @GetMapping("/status-count")
    public ResponseEntity<Map<String, Long>> getLoanStatusCounts() {
        return ResponseEntity.ok(loanService.getLoanStatusCounts());
    }

    @GetMapping
    public Page<Loan> getLoansByStatus(
            @RequestParam LoanStatus status,
            @RequestParam int page,
            @RequestParam int size
    ) {
       return loanService.getLoansByStatus(status, page, size);
    }

}
