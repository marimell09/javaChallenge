package com.javaChallenge.JavaChallenge.service;

import com.javaChallenge.JavaChallenge.dto.UpdateCustomerDto;
import com.javaChallenge.JavaChallenge.exception.ResourceDuplicatedException;
import com.javaChallenge.JavaChallenge.exception.ResourceNotFoundException;
import com.javaChallenge.JavaChallenge.model.Customer;
import com.javaChallenge.JavaChallenge.repository.CustomerRepository;
import com.javaChallenge.JavaChallenge.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private final Customer customer = new Customer(null,
            "TestName",
            "TestLastName",
            "TestUsername",
            "TesteEmail@outlook.com",
            "1234",
            new ArrayList<>());

    final Customer updatedCustomer = new Customer(null,
            "TestNameUpdate",
            "TestLastNameUpdate",
            "TestUsername",
            "TesteEmail@outlook.com",
            "1234",
            new ArrayList<>());

    final UpdateCustomerDto updateDto = new UpdateCustomerDto(updatedCustomer.getFirstName(), updatedCustomer.getLastName());

    @Test
    void shouldCreateCustomerSuccessfully() throws ResourceDuplicatedException {
        given(customerRepository.findByUsername(customer.getUsername())).willReturn(null);
        given(customerRepository.save(customer)).willAnswer(invocation -> invocation.getArgument(0));

        Customer savedCustomer = customerService.create(customer);

        assertThat(savedCustomer).isNotNull();

        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    void shouldThrowExceptionWhenCreateCustomerWithExistingUsername(){
        given(customerRepository.findByUsername(customer.getUsername())).willReturn(customer);

        assertThrows(ResourceDuplicatedException.class,() -> {
          customerService.create(customer);
        });

        verify(customerRepository, never()).save(any(Customer.class));
    }

    @Test
    void updateCustomer() throws ResourceNotFoundException {

        given(customerRepository.findByUsername(customer.getUsername())).willReturn(customer);
        when(customerRepository.save(any())).thenReturn(updatedCustomer);

        final Customer updatedResult = customerService.update(customer.getUsername(), updateDto);
        assertThat(updatedResult).isNotNull();
        verify(customerRepository).save(any(Customer.class));

    }

    @Test
    void listAll(){
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        PageImpl<Customer> pageCustomer = new PageImpl<>(customers);
        when(customerRepository.findAll(any(Pageable.class))).thenReturn(pageCustomer);

        Page<Customer> pageResult = customerService.listAll(Pageable.ofSize(1));
        assertThat(pageResult).isNotNull();
        verify(customerRepository).findAll(any(Pageable.class));
    }

    @Test
    void shouldDelete() throws ResourceNotFoundException {
        given(customerRepository.findByUsername(customer.getUsername())).willReturn(customer);

        customerService.delete(customer.getUsername());
        verify(customerRepository, times(1)).delete(customer);
    }

    @Test
    void shouldThrowExceptionWhenDeleteCustomerWithCustomerNonExistent(){
        given(customerRepository.findByUsername(customer.getUsername())).willReturn(null);

        assertThrows(ResourceNotFoundException.class,() -> {
            customerService.delete(customer.getUsername());
        });

        verify(customerRepository, never()).save(any(Customer.class));
    }
}
