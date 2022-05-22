package com.javaChallenge.JavaChallenge.controller;

import com.javaChallenge.JavaChallenge.authentication.CognitoService;
import com.javaChallenge.JavaChallenge.dto.AuthenticationRequestDto;
import com.javaChallenge.JavaChallenge.model.Customer;
import com.javaChallenge.JavaChallenge.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/login")
public class AuthenticationController {

    @Autowired
    private CustomerRepository customerRepository;
    private AuthenticationRequestDto authenticationRequestDto;
    private CognitoService cognitoService;

    @PostMapping
    public Map<String, String> login(AuthenticationRequestDto requestDto) {
        return cognitoService.login(requestDto.getEmail(), requestDto.getPassword());
    }
}
