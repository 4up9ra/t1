package com.example.t1.project.payment.validator;

import com.example.t1.project.payment.dto.PaymentRequestDto;
import com.example.t1.project.payment.exception.NotEnoughBalanceException;
import com.example.t1.project.payment.exception.NotCorrectAccountNumberException;
import com.example.t1.project.product.dto.ProductResponseDto;
import com.example.t1.project.product.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ResponseValidator {

    public void validateResponse(ProductResponseDto product, PaymentRequestDto paymentRequestDto) {
        if (product == null) {
            throw new ProductNotFoundException("Product with productId='" + paymentRequestDto.getProductId() + "' not found");
        }

        if (!Objects.equals(product.getAccountNumber(), paymentRequestDto.getAccountNumber())) {
            throw new NotCorrectAccountNumberException("Account number not correct");
        }

        if (paymentRequestDto.getAmount().compareTo(product.getBalance()) > 0) {
            throw new NotEnoughBalanceException("Requested amount bigger than balance");
        }
    }
}