package com.javaChallenge.JavaChallenge.services;

import com.javaChallenge.JavaChallenge.model.Role;
import com.javaChallenge.JavaChallenge.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;


    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public Role create(Role role){
        Role existsRole = roleRepository.findByName(role.getName());

        if (existsRole != null){
            throw new Error("Role already exists!");
        }

        Role createdRole = roleRepository.save(role);

        return createdRole;
    }
}
