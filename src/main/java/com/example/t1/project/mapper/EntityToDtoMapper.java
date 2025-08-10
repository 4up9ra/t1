package com.example.t1.project.mapper;

import com.example.t1.project.dto.ProductResponseDto;
import com.example.t1.project.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class EntityToDtoMapper {

    public ProductResponseDto mapToDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setAccountNumber(product.getAccountNumber());
        productResponseDto.setBalance(product.getBalance());
        productResponseDto.setUserId(product.getId());
        return productResponseDto;
    }
}