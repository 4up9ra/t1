package com.example.t1.project.service;

import com.example.t1.project.dto.ProductResponseDto;
import com.example.t1.project.entity.Product;
import com.example.t1.project.exception.ProductNotFoundException;
import com.example.t1.project.mapper.EntityToDtoMapper;
import com.example.t1.project.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}