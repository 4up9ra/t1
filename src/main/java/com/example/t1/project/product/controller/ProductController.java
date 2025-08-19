package com.example.t1.project.product.controller;

import com.example.t1.project.product.dto.ProductResponseDto;
import com.example.t1.project.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getByUserId")
    public ResponseEntity<List<ProductResponseDto>> getByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok().body(productService.findByUserId(userId));
    }

    @GetMapping("/getByProductId")
    public ResponseEntity<ProductResponseDto> getByProductId(@RequestParam Long productId) {
        return ResponseEntity.ok().body(productService.findByProductId(productId));
    }

    @PostMapping("/payByProductId")
    public ResponseEntity<ProductResponseDto> payByProductId(@RequestParam Long productId,
                                                             @RequestParam BigDecimal amount) {
        return ResponseEntity.ok().body(productService.payByProductId(productId, amount));
    }
}