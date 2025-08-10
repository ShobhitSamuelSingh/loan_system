package com.shobhit.loan_system.service;

import com.shobhit.loan_system.model.entity.Customer;
import com.shobhit.loan_system.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void saveEntry(Customer newCustomer) {
        customerRepository.save(newCustomer);
    }

    public List<Customer> getAll() { return customerRepository.findAll(); }


}
