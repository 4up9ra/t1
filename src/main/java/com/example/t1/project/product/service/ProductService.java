package com.example.t1.project.product.service;

import com.example.t1.project.product.dto.ProductResponseDto;
import com.example.t1.project.product.entity.Product;
import com.example.t1.project.product.exception.ProductNotFoundException;
import com.example.t1.project.product.mapper.EntityToDtoMapper;
import com.example.t1.project.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final EntityToDtoMapper entityToDtoMapper;

    public ProductService(ProductRepository productRepository, EntityToDtoMapper entityToDtoMapper) {
        this.productRepository = productRepository;
        this.entityToDtoMapper = entityToDtoMapper;
    }

    public List<ProductResponseDto> findByUserId(Long userId) {
        List<Product> products = productRepository.findByUserId(userId);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("Products for User with id = '" + userId + "' not found");
        }

        return products.stream()
                .map(entityToDtoMapper::mapToDto)
                .toList();
    }

    public ProductResponseDto findByProductId(Long productId) {
        return productRepository.findById(productId)
               .map(entityToDtoMapper::mapToDto)
               .orElseThrow(() -> new ProductNotFoundException("Product with id = '" + productId + "' not found"));
    }

    @Transactional
    public ProductResponseDto payByProductId(Long productId, BigDecimal amount) {
        return productRepository.updateBalanceById(productId, amount);
    }
}