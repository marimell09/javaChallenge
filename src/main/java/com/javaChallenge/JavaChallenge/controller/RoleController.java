package com.javaChallenge.JavaChallenge.controller;

import com.javaChallenge.JavaChallenge.exception.ResourceDuplicatedException;
import com.javaChallenge.JavaChallenge.model.Role;
import com.javaChallenge.JavaChallenge.repository.RoleRepository;
import com.javaChallenge.JavaChallenge.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/roles")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleService roleService;


    @PostMapping
    public ResponseEntity<Role> create(@RequestBody Role role) throws ResourceDuplicatedException {
        return ResponseEntity.ok(roleService.create(role));
    }

    @GetMapping
    public ResponseEntity<List<Role>> getRoles(){
        return ResponseEntity.ok(roleRepository.findAll());
    }
}
