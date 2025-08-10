package com.shobhit.loan_system.controller;

import com.shobhit.loan_system.model.entity.Customer;
import com.shobhit.loan_system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public void createCustomer(@RequestBody Customer newCustomer) {
        customerService.saveEntry(newCustomer);
    }

    @GetMapping("/getAll")
    public List<Customer> getAll() { return customerService.getAll(); }

    @GetMapping("/top")
    public ResponseEntity<List<Map<String, Object>>> getTopCustomers() {
        return ResponseEntity.ok(customerService.getTopCustomers());
    }
}
