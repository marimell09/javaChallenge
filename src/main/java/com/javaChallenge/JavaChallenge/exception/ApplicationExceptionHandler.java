package com.javaChallenge.JavaChallenge.exception;

import com.javaChallenge.JavaChallenge.dto.DefaultErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceDuplicatedException.class)
    public ResponseEntity handleException(ResourceDuplicatedException e){
        log.error(e.getMessage(), e.getStackTrace());
        DefaultErrorDto error = new DefaultErrorDto(HttpStatus.CONFLICT,  e.getMessage());
        return new ResponseEntity(error, error.getStatus());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleException(ResourceNotFoundException e){
        log.error(e.getMessage(), e.getStackTrace());
        DefaultErrorDto error = new DefaultErrorDto(HttpStatus.NOT_FOUND,  e.getMessage());
        return new ResponseEntity(error, error.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e){
        log.error(e.getMessage(), e.getStackTrace());
        DefaultErrorDto error = new DefaultErrorDto(HttpStatus.BAD_REQUEST,  e.getMessage());
        return new ResponseEntity(error, error.getStatus());
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity handleException(HttpServerErrorException.InternalServerError e){
        log.error(e.getMessage(), e.getStackTrace());
        DefaultErrorDto error = new DefaultErrorDto(HttpStatus.INTERNAL_SERVER_ERROR,  e.getMessage());
        return new ResponseEntity(error, error.getStatus());
    }

}
