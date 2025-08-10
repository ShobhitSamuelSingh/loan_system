package com.shobhit.loan_system.controller;


import com.shobhit.loan_system.dto.LoanRequest;
import com.shobhit.loan_system.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/v1")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/loans")
    public void loanApplication(@RequestBody LoanRequest loanReq ) {
        loanService.applyLoan(loanReq);
    }
}
