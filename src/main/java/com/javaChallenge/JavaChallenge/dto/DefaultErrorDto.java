package com.javaChallenge.JavaChallenge.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class DefaultErrorDto {

    private HttpStatus status;
    private String message;
    private int code;

    public DefaultErrorDto(HttpStatus status, String message){
        this.status = status;
        this.message = message;
        this.code = status.value();
    }
}
