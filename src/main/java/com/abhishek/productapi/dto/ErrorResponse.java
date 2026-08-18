package com.abhishek.productapi.dto;

import java.util.Map;

/**
 * @author siabhis
 **/
public  class ErrorResponse {
    private String message;
    private Map<String,String> errors;
    public ErrorResponse() {

    }
    public ErrorResponse(String message, Map<String,String> error) {
        this.message = message;
        this.errors = error;

    }
    public String getMessage() {
        return message;
    }

    public Map<String,String> getError() {
        return errors;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
