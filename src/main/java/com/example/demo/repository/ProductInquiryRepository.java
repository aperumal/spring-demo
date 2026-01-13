package com.example.demo.repository;

import com.example.demo.entity.ProductInquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductInquiryRepository extends JpaRepository<ProductInquiry, Long> {
    List<ProductInquiry> findByProductId(Long productId);
    List<ProductInquiry> findByUserId(Long userId);
    List<ProductInquiry> findByStatus(String status);
    List<ProductInquiry> findByPriority(String priority);
    List<ProductInquiry> findByInquiryType(String inquiryType);
    List<ProductInquiry> findByProductIdAndStatus(Long productId, String status);
    List<ProductInquiry> findByUserIdAndStatus(Long userId, String status);
    List<ProductInquiry> findByStatusAndPriority(String status, String priority);
    long countByStatus(String status);
}
