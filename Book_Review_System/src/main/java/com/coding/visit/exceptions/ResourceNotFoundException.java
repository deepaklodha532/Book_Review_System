package com.coding.visit.exceptions;

import org.springframework.core.io.Resource;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {
        super("user not found ");
    }

    public ResourceNotFoundException(String message){
        super(message) ;
    }
}
