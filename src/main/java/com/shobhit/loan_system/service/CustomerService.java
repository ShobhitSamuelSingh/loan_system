package com.shobhit.loan_system.service;

import com.shobhit.loan_system.model.entity.Customer;
import com.shobhit.loan_system.model.enums.LoanStatus;
import com.shobhit.loan_system.repository.CustomerRepository;
import com.shobhit.loan_system.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanRepository loanRepository;

    public void saveEntry(Customer newCustomer) {
        customerRepository.save(newCustomer);
    }

    public List<Customer> getAll() { return customerRepository.findAll(); }

    public List<Map<String,Object>> getTopCustomers() {
        List<LoanStatus> approvedStatuses = List.of(
                LoanStatus.APPROVED_BY_SYSTEM,
                LoanStatus.APPROVED_BY_AGENT
        );

        Pageable topThree = PageRequest.of(0,3);

        List<Object[]> results = loanRepository.findTopCustomersByApprovedLoans(approvedStatuses, topThree);

        List<Map<String, Object>> topCustomers = new ArrayList<>();
        for(Object[] row : results) {
            Map<String, Object> customerData = new HashMap<>();
            customerData.put("customerName", row[0]);
            customerData.put("approvedLoanCount", row[1]);
            topCustomers.add(customerData);
        }
        return topCustomers;
    }
}
