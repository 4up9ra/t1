package com.example.t1.project.payment.controller;

import com.example.t1.project.payment.dto.PaymentRequestDto;
import com.example.t1.project.payment.service.PaymentService;
import com.example.t1.project.product.dto.ProductResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/getProductsByUserId")
    public ResponseEntity<List<ProductResponseDto>> getProductsByUserId(@RequestParam Long userId) {
        return paymentService.getProductsByUserId(userId);
    }

    @PostMapping("/pay")
    public ResponseEntity<String> pay(@RequestBody @Valid PaymentRequestDto paymentRequestDto) {
        return ResponseEntity.ok(paymentService.pay(paymentRequestDto));
    }
}