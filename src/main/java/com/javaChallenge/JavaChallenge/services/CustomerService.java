package com.javaChallenge.JavaChallenge.services;

import com.javaChallenge.JavaChallenge.dto.UpdateCustomerDto;
import com.javaChallenge.JavaChallenge.model.Customer;
import com.javaChallenge.JavaChallenge.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public Customer create(Customer customer){
        Customer existsCustomer = customerRepository.findByUsername(customer.getUsername());

        if (existsCustomer != null){
            throw new Error("Customer already exists!");
        }

        customer.setPassword(passwordEncoder().encode(customer.getPassword()));
        Customer createdCustomer = customerRepository.save(customer);
        return createdCustomer;
    }

    public Page<Customer> listAll(Pageable pageable){
        return customerRepository.findAll(pageable);
    }

    public Customer update(String username, UpdateCustomerDto updateCustomerDto){
        Customer existsCustomer = customerRepository.findByUsername(username);

        if(existsCustomer == null){
            throw new Error("Couldn't update customer!");
        }

        existsCustomer.setFirstName(updateCustomerDto.getFirstName());
        existsCustomer.setLastName(updateCustomerDto.getLastName());

        Customer updatedCustomer = customerRepository.save(existsCustomer);

        return updatedCustomer;
    }

    public void delete(String username){
        Customer existsCustomer = customerRepository.findByUsername(username);

        if(existsCustomer == null){
            throw new Error("Couldn't delete customer!");
        }

        customerRepository.delete(existsCustomer);
    }
}
