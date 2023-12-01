package com.udemy.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Builder
@AllArgsConstructor
public class CustomErrorResponse {

    private String message;
    private HttpStatus status;
    private LocalDateTime dateTime;
}
