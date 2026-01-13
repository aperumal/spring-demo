package com.example.demo.service;

import com.example.demo.entity.ProductInquiry;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repository.ProductInquiryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductInquiryService {
    @Autowired
    private ProductInquiryRepository productInquiryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public ProductInquiry createInquiry(ProductInquiry inquiry) {
        return productInquiryRepository.save(inquiry);
    }

    public Optional<ProductInquiry> getInquiryById(Long id) {
        return productInquiryRepository.findById(id);
    }

    public List<ProductInquiry> getAllInquiries() {
        return productInquiryRepository.findAll();
    }

    public List<ProductInquiry> getInquiriesByProductId(Long productId) {
        return productInquiryRepository.findByProductId(productId);
    }

    public List<ProductInquiry> getInquiriesByUserId(Long userId) {
        return productInquiryRepository.findByUserId(userId);
    }

    public List<ProductInquiry> getInquiriesByStatus(String status) {
        return productInquiryRepository.findByStatus(status);
    }

    public List<ProductInquiry> getPendingInquiries() {
        return productInquiryRepository.findByStatus("PENDING");
    }

    public List<ProductInquiry> getAnsweredInquiries() {
        return productInquiryRepository.findByStatus("ANSWERED");
    }

    public List<ProductInquiry> getResolvedInquiries() {
        return productInquiryRepository.findByStatus("RESOLVED");
    }

    public List<ProductInquiry> getInquiriesByPriority(String priority) {
        return productInquiryRepository.findByPriority(priority);
    }

    public List<ProductInquiry> getInquiriesByType(String inquiryType) {
        return productInquiryRepository.findByInquiryType(inquiryType);
    }

    public List<ProductInquiry> getInquiriesByProductAndStatus(Long productId, String status) {
        return productInquiryRepository.findByProductIdAndStatus(productId, status);
    }

    public List<ProductInquiry> getInquiriesByUserAndStatus(Long userId, String status) {
        return productInquiryRepository.findByUserIdAndStatus(userId, status);
    }

    public List<ProductInquiry> getInquiriesByStatusAndPriority(String status, String priority) {
        return productInquiryRepository.findByStatusAndPriority(status, priority);
    }

    public ProductInquiry updateInquiry(Long id, ProductInquiry inquiryDetails) {
        return productInquiryRepository.findById(id).map(inquiry -> {
            if (inquiryDetails.getSubject() != null) inquiry.setSubject(inquiryDetails.getSubject());
            if (inquiryDetails.getMessage() != null) inquiry.setMessage(inquiryDetails.getMessage());
            if (inquiryDetails.getInquiryType() != null) inquiry.setInquiryType(inquiryDetails.getInquiryType());
            if (inquiryDetails.getStatus() != null) inquiry.setStatus(inquiryDetails.getStatus());
            if (inquiryDetails.getPriority() != null) inquiry.setPriority(inquiryDetails.getPriority());
            return productInquiryRepository.save(inquiry);
        }).orElse(null);
    }

    public ProductInquiry answerInquiry(Long id, String response) {
        return productInquiryRepository.findById(id).map(inquiry -> {
            inquiry.setStatus("ANSWERED");
            inquiry.setResponse(response);
            inquiry.setResponseDate(LocalDateTime.now());
            return productInquiryRepository.save(inquiry);
        }).orElse(null);
    }

    public ProductInquiry resolveInquiry(Long id) {
        return productInquiryRepository.findById(id).map(inquiry -> {
            inquiry.setStatus("RESOLVED");
            inquiry.setResolvedAt(LocalDateTime.now());
            return productInquiryRepository.save(inquiry);
        }).orElse(null);
    }

    public void deleteInquiry(Long id) {
        productInquiryRepository.deleteById(id);
    }

    public long getTotalInquiries() {
        return productInquiryRepository.count();
    }

    public long getPendingInquiriesCount() {
        return productInquiryRepository.countByStatus("PENDING");
    }

    public long getAnsweredInquiriesCount() {
        return productInquiryRepository.countByStatus("ANSWERED");
    }

    public long getResolvedInquiriesCount() {
        return productInquiryRepository.countByStatus("RESOLVED");
    }
}
