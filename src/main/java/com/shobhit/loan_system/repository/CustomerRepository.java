package com.shobhit.loan_system.repository;

import com.shobhit.loan_system.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
