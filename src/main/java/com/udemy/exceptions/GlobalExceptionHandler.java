package com.udemy.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<CustomErrorResponse> handle(DataIntegrityViolationException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(CustomErrorResponse.builder()
                        .message(e.getMessage())
                        .dateTime(LocalDateTime.now())
                        .status(HttpStatus.CONFLICT)
                .build());
    }

}
