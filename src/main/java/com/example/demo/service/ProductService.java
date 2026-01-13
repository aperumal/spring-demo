package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product getProductBySku(String sku) {
        return productRepository.findBySku(sku).orElse(null);
    }

    public Product getProductByName(String name) {
        return productRepository.findByName(name).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getActiveProducts() {
        return productRepository.findByIsActive(true);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getActiveProductsByCategory(String category) {
        return productRepository.findByCategoryAndIsActive(category, true);
    }

    public Product updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id).map(product -> {
            if (productDetails.getName() != null) product.setName(productDetails.getName());
            if (productDetails.getDescription() != null) product.setDescription(productDetails.getDescription());
            if (productDetails.getPrice() != null) product.setPrice(productDetails.getPrice());
            if (productDetails.getStockQuantity() != null) product.setStockQuantity(productDetails.getStockQuantity());
            if (productDetails.getSku() != null) product.setSku(productDetails.getSku());
            if (productDetails.getCategory() != null) product.setCategory(productDetails.getCategory());
            if (productDetails.getIsActive() != null) product.setIsActive(productDetails.getIsActive());
            return productRepository.save(product);
        }).orElse(null);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public long getTotalProducts() {
        return productRepository.count();
    }

    public long getActiveProductsCount() {
        return getActiveProducts().size();
    }
}
