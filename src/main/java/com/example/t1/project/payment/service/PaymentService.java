package com.example.t1.project.payment.service;

import com.example.t1.project.payment.dto.PaymentRequestDto;
import com.example.t1.project.payment.validator.ResponseValidator;
import com.example.t1.project.product.dto.ProductResponseDto;
import com.example.t1.project.product.exception.ProductNotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class PaymentService {

    private final RestTemplate restTemplate;
    private final ResponseValidator responseValidator;

    private static final String GET_PRODUCTS_BY_USER_ID_URL = "http://localhost:8080/products/getByUserId";
    private static final String GET_PRODUCTS_BY_PRODUCT_ID_URL = "http://localhost:8080/products/getByProductId";
    private static final String PAY_BY_PRODUCT_ID_URL = "http://localhost:8080/products/payByProductId";

    public PaymentService(RestTemplate restTemplate, ResponseValidator responseValidator) {
        this.restTemplate = restTemplate;
        this.responseValidator = responseValidator;
    }

    public String pay(PaymentRequestDto paymentRequestDto) {
        try {
            String url = UriComponentsBuilder.fromUriString(GET_PRODUCTS_BY_PRODUCT_ID_URL)
                    .queryParam("productId", paymentRequestDto.getProductId())
                    .toUriString();
            ResponseEntity<ProductResponseDto> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    ProductResponseDto.class);
            responseValidator.validateResponse(response.getBody(), paymentRequestDto);
            return payByProductId(paymentRequestDto);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ProductNotFoundException("Product with productId='" + paymentRequestDto.getProductId() + "' not found");
        }
    }

    private String payByProductId(PaymentRequestDto paymentRequestDto) {
        String url = UriComponentsBuilder.fromUriString(PAY_BY_PRODUCT_ID_URL)
                .queryParam("productId", paymentRequestDto.getProductId())
                .queryParam("amount", paymentRequestDto.getAmount())
                .toUriString();
        ResponseEntity<ProductResponseDto> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                null,
                ProductResponseDto.class);
        return "Payment accepted, current balance='" + response.getBody().getBalance() + "'";
    }

    public ResponseEntity<List<ProductResponseDto>> getProductsByUserId(Long userId) {
        try {
            String url = UriComponentsBuilder.fromUriString(GET_PRODUCTS_BY_USER_ID_URL)
                    .queryParam("userId", userId)
                    .toUriString();
            return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    });
        } catch (HttpClientErrorException.NotFound e) {
            throw new ProductNotFoundException("Products for User with id = '" + userId + "' not found");
        }
    }
}
