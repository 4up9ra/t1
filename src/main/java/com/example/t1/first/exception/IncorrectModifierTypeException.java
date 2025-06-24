package com.example.t1.first.exception;

public class IncorrectModifierTypeException extends RuntimeException {
    public IncorrectModifierTypeException(String message) {
        super(message);
    }
}
