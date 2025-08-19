package com.example.t1.project.payment.exception;

public class NotCorrectAccountNumberException extends RuntimeException{
    public NotCorrectAccountNumberException(String message) {
        super(message);
    }
}