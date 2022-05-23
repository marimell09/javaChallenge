package com.javaChallenge.JavaChallenge.service;

import com.javaChallenge.JavaChallenge.dto.CreateCustomerRoleDto;
import com.javaChallenge.JavaChallenge.model.Customer;
import com.javaChallenge.JavaChallenge.repository.CustomerRepository;
import com.javaChallenge.JavaChallenge.services.CustomerRoleService;
import com.javaChallenge.JavaChallenge.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CustomerRoleServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerRoleService customerRoleService;

    final UUID customerId = UUID.randomUUID();
    final UUID roleId = UUID.randomUUID();
    final List<UUID> roleIds = Arrays.asList(roleId);

    private final Customer customer = new Customer(null,
            "TestName",
            "TestLastName",
            "TestUsername",
            "TesteEmail@outlook.com",
            "1234",
            new ArrayList<>());


    @Test
    void shouldCreateCustomerRoleSuccessfully(){
        given(customerRepository.findById(customerId)).willReturn(Optional.of(customer));

        given(customerRepository.save(customer)).willAnswer(invocation -> invocation.getArgument(0));

        CreateCustomerRoleDto customerRole = new CreateCustomerRoleDto();
        customerRole.setIdsRoles(roleIds);
        customerRole.setIdUser(customerId);

        Customer savedCustomer = customerRoleService.create(customerRole);

        assertThat(savedCustomer).isNotNull();
        verify(customerRepository).save(any(Customer.class));
    }
}
