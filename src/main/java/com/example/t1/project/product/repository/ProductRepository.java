package com.example.t1.project.product.repository;

import com.example.t1.project.product.dto.ProductResponseDto;
import com.example.t1.project.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByUserId(Long userId);

    Optional<Product> findById(Long productId);

    @Query(value = """
            UPDATE products SET balance = balance - :amount WHERE id = :id
            RETURNING *
            """,
            nativeQuery = true)
    ProductResponseDto updateBalanceById(Long id, BigDecimal amount);
}