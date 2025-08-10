package com.shobhit.loan_system.repository;

import com.shobhit.loan_system.model.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
