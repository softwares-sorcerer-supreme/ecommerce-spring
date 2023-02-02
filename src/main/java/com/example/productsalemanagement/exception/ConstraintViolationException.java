package com.example.productsalemanagement.exception;

public class ConstraintViolationException extends RuntimeException{
    public ConstraintViolationException() {
    }

    public ConstraintViolationException(String message) {
        super(message);
    }

    public ConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
