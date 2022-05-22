package com.javaChallenge.JavaChallenge.services;

import com.javaChallenge.JavaChallenge.model.Customer;
import com.javaChallenge.JavaChallenge.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer create(Customer customer){
        Customer existsCustomer = customerRepository.findByEmail(customer.getEmail());

        if (existsCustomer != null){
            throw new Error("User already exists!");
        }

        Customer createdCustomer = customerRepository.save(customer);

        return createdCustomer;
    }
}
