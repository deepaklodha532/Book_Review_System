package com.coding.visit.exceptions;

import com.coding.visit.dtos.ApiResponseMessage;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseMessage> handleResource(ResourceNotFoundException e){
        ApiResponseMessage message = new ApiResponseMessage();
        message.setMessage(e.getMessage());
        message.setSuccess(false) ;
        message.setStatus(HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(message);
    }
}
