package com.shobhit.loan_system.repository;

import com.shobhit.loan_system.model.entity.Loan;
import com.shobhit.loan_system.model.enums.LoanStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findTop10ByLoanStatus(LoanStatus status);

    @Query("SELECT l.loanStatus, COUNT(l) FROM Loan l GROUP BY l.loanStatus")
    List<Object[]> countLoansByStatus();

    @Query("""
        SELECT l.customer.cust_name, COUNT(l)
        FROM Loan l
        WHERE l.loanStatus IN (:approvedStatuses)
        GROUP BY l.customer.cust_name
        ORDER BY COUNT(l) DESC
    """)
    List<Object[]> findTopCustomersByApprovedLoans(
        @Param("approvedStatuses") List<LoanStatus> approvedStatuses,
        Pageable pageable
    );

    Page<Loan> findByLoanStatus(LoanStatus status, Pageable pageable);
}
