package com.javaChallenge.JavaChallenge.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CreateCustomerRoleDto {

    private UUID idUser;

    private List<UUID> idsRoles;

    public CreateCustomerRoleDto() {
    }
}