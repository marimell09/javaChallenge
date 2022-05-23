package com.javaChallenge.JavaChallenge.services;

import com.javaChallenge.JavaChallenge.dto.UpdateCustomerDto;
import com.javaChallenge.JavaChallenge.exception.ResourceDuplicatedException;
import com.javaChallenge.JavaChallenge.exception.ResourceNotFoundException;
import com.javaChallenge.JavaChallenge.model.Customer;
import com.javaChallenge.JavaChallenge.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Create customer
     * @param customer information to be created
     * @return the customer created
     * @throws ResourceDuplicatedException if customer already exists
     */
    public Customer create(Customer customer) throws ResourceDuplicatedException {
        Customer customerFound = customerRepository.findByUsername(customer.getUsername());

        if (!checkCustomerExistence(customerFound)){
            throw new ResourceDuplicatedException("Customer already exist!");
        }

        customer.setPassword(passwordEncoder().encode(customer.getPassword()));
        Customer createdCustomer = customerRepository.save(customer);
        return createdCustomer;
    }

    /**
     * List all the customers, using pagination based on user parameters.
     * @param pageable the user parameters e.g.: size or page.
     * @return The list of all customers based on the pagination
     */
    public Page<Customer> listAll(Pageable pageable){
        return customerRepository.findAll(pageable);
    }

    /**
     * Update customer information. The information allowed to be updated are only the first name and last name.
     * @param username the customer identifier in order for the update to happen
     * @param updateCustomerDto the customer information to be updated
     * @return the customer updated
     * @throws ResourceDuplicatedException if customer doesn't exist
     */
    public Customer update(String username, UpdateCustomerDto updateCustomerDto) throws ResourceNotFoundException {
        Customer customerFound = customerRepository.findByUsername(username);

        if (checkCustomerExistence(customerFound)){
            throw new ResourceNotFoundException("Customer doesn't exist!");
        }

        customerFound.setFirstName(updateCustomerDto.getFirstName());
        customerFound.setLastName(updateCustomerDto.getLastName());

        Customer updatedCustomer = customerRepository.save(customerFound);

        return updatedCustomer;
    }

    /**
     * Delete customer
     * @param username the customer identifier in order for the deletion to happen
     * @throws ResourceDuplicatedException if the customer doesn't exist
     */
    public void delete(String username) throws ResourceNotFoundException {
        Customer customerFound = customerRepository.findByUsername(username);

        if (checkCustomerExistence(customerFound)){
            throw new ResourceNotFoundException("Customer doesn't exist!");
        }

        customerRepository.delete(customerFound);
    }

    /**
     * Check if customer exists.
     * @param customer - the customer object
     * @return true if customer does exists, or false if it doesn't.
     */
    public boolean checkCustomerExistence(Customer customer){
        if(customer != null){
            return false;
        }
        return true;
    }
}
