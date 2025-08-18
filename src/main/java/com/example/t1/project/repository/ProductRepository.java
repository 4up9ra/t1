package com.example.t1.project.repository;

import com.example.t1.project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByUserId(Long userId);

    Optional<Product> findById(Long productId);
}