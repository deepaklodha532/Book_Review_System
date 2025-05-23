package com.coding.visit.dtos;

import lombok.Data;
import org.springframework.http.HttpStatus;

public class ApiResponseMessage {
    String message;
    HttpStatus status ;
    boolean success ;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
