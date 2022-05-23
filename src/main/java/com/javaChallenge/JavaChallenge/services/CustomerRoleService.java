package com.javaChallenge.JavaChallenge.services;

import com.javaChallenge.JavaChallenge.dto.CreateCustomerRoleDto;
import com.javaChallenge.JavaChallenge.exception.ResourceNotFoundException;
import com.javaChallenge.JavaChallenge.model.Customer;
import com.javaChallenge.JavaChallenge.model.Role;
import com.javaChallenge.JavaChallenge.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerRoleService {

    @Autowired
    CustomerRepository customerRepository;


    /**
     * Attributed roles to the customer.
     * @param createCustomerRoleDTO the customer and roles information for the attribution
     * @return the customer with it's attributed roles
     * @throws ResourceNotFoundException when customer was not found
     */
    public Customer create(CreateCustomerRoleDto createCustomerRoleDTO) throws ResourceNotFoundException {

        Optional<Customer> customerExists = customerRepository.findById(createCustomerRoleDTO.getIdUser());
        List<Role> roles = new ArrayList<>();

        if (customerExists.isEmpty()) {
            throw new ResourceNotFoundException("Customer does not exist!");
        }

        roles = createCustomerRoleDTO.getIdsRoles().stream().map(role -> {
            return new Role(role);
        }).collect(Collectors.toList());

        Customer customer = customerExists.get();

        customer.setRoles(roles);

        customerRepository.save(customer);

        return customer;

    }
}
