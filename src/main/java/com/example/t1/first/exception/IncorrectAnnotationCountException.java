package com.example.t1.first.exception;

public class IncorrectAnnotationCountException extends RuntimeException {
    public IncorrectAnnotationCountException(String message) {
        super(message);
    }
}
