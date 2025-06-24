package com.example.t1.first.exception;

public class IllegalAnnotationArgumentException extends RuntimeException {
    public IllegalAnnotationArgumentException(String message) {
        super(message);
    }
}
