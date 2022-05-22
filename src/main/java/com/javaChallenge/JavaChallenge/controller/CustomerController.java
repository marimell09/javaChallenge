package com.javaChallenge.JavaChallenge.controller;

import com.javaChallenge.JavaChallenge.dto.CreateCustomerRoleDto;
import com.javaChallenge.JavaChallenge.dto.UpdateCustomerDto;
import com.javaChallenge.JavaChallenge.model.Customer;
import com.javaChallenge.JavaChallenge.repository.CustomerRepository;
import com.javaChallenge.JavaChallenge.services.CustomerRoleService;
import com.javaChallenge.JavaChallenge.services.CustomerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRoleService customerRoleService;

    @GetMapping
    public ResponseEntity<Page<Customer>> getAllCustomers(Pageable pageable){
        return ResponseEntity.ok(customerService.listAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer){
        return ResponseEntity.ok(customerService.create(customer));
    }

    @PutMapping(value="/{username}")
    public ResponseEntity<Customer> update(@PathVariable String username, @RequestBody UpdateCustomerDto updateCustomerDto){
        return ResponseEntity.ok(customerService.update(username, updateCustomerDto));
    }

    @PostMapping("/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Customer> role(@RequestBody CreateCustomerRoleDto createCustomerRoleDTO){
        return ResponseEntity.ok(customerRoleService.create(createCustomerRoleDTO));
    }
}
