package com.shobhit.loan_system.dto;

import com.shobhit.loan_system.model.enums.LoanType;
import lombok.Data;

@Data
public class LoanRequest {
    private Long customerId;
    private Double loanAmount;
    private LoanType loanType;
}
