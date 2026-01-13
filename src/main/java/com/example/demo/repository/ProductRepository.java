package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySku(String sku);
    Optional<Product> findByName(String name);
    List<Product> findByCategory(String category);
    List<Product> findByIsActive(Boolean isActive);
    List<Product> findByCategoryAndIsActive(String category, Boolean isActive);
}
