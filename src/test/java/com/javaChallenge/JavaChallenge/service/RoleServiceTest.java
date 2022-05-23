package com.javaChallenge.JavaChallenge.service;

import com.javaChallenge.JavaChallenge.exception.ResourceDuplicatedException;
import com.javaChallenge.JavaChallenge.model.Customer;
import com.javaChallenge.JavaChallenge.model.Role;
import com.javaChallenge.JavaChallenge.repository.CustomerRepository;
import com.javaChallenge.JavaChallenge.repository.RoleRepository;
import com.javaChallenge.JavaChallenge.services.CustomerService;
import com.javaChallenge.JavaChallenge.services.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    final Role role = new Role(null, "ADMIN");

    @Test
    void shouldCreateRoleSuccessfully() throws ResourceDuplicatedException {
        given(roleRepository.findByName(role.getName())).willReturn(null);

        given(roleRepository.save(role)).willAnswer(invocation -> invocation.getArgument(0));

        Role savedRole = roleService.create(role);

        assertThat(savedRole).isNotNull();
        verify(roleRepository).save(any(Role.class));
    }

    @Test
    void shouldThrowExceptionWhenRoleAlreadyExist(){
        given(roleRepository.findByName(role.getName())).willReturn(role);

        assertThrows(ResourceDuplicatedException.class,() -> {
            roleService.create(role);
        });
        verify(roleRepository, never()).save(any(Role.class));
    }
}
