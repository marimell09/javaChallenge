package com.javaChallenge.JavaChallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UpdateCustomerDto {
    private String firstName;
    private String lastName;
}




