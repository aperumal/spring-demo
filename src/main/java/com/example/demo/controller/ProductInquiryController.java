package com.example.demo.controller;

import com.example.demo.entity.ProductInquiry;
import com.example.demo.service.ProductInquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product-inquiries")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductInquiryController {
    @Autowired
    private ProductInquiryService productInquiryService;

    @PostMapping
    public ResponseEntity<ProductInquiry> createInquiry(@RequestBody ProductInquiry inquiry) {
        ProductInquiry createdInquiry = productInquiryService.createInquiry(inquiry);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInquiry);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductInquiry> getInquiryById(@PathVariable Long id) {
        return productInquiryService.getInquiryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProductInquiry>> getAllInquiries() {
        List<ProductInquiry> inquiries = productInquiryService.getAllInquiries();
        return ResponseEntity.ok(inquiries);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductInquiry>> getInquiriesByProductId(@PathVariable Long productId) {
        List<ProductInquiry> inquiries = productInquiryService.getInquiriesByProductId(productId);
        return ResponseEntity.ok(inquiries);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProductInquiry>> getInquiriesByUserId(@PathVariable Long userId) {
        List<ProductInquiry> inquiries = productInquiryService.getInquiriesByUserId(userId);
        return ResponseEntity.ok(inquiries);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ProductInquiry>> getInquiriesByStatus(@PathVariable String status) {
        List<ProductInquiry> inquiries = productInquiryService.getInquiriesByStatus(status);
        return ResponseEntity.ok(inquiries);
    }

    @GetMapping("/status/pending")
    public ResponseEntity<List<ProductInquiry>> getPendingInquiries() {
        List<ProductInquiry> inquiries = productInquiryService.getPendingInquiries();
        return ResponseEntity.ok(inquiries);
    }

    @GetMapping("/status/answered")
    public ResponseEntity<List<ProductInquiry>> getAnsweredInquiries() {
        List<ProductInquiry> inquiries = productInquiryService.getAnsweredInquiries();
        return ResponseEntity.ok(inquiries);
    }

    @GetMapping("/status/resolved")
    public ResponseEntity<List<ProductInquiry>> getResolvedInquiries() {
        List<ProductInquiry> inquiries = productInquiryService.getResolvedInquiries();
        return ResponseEntity.ok(inquiries);
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<ProductInquiry>> getInquiriesByPriority(@PathVariable String priority) {
        List<ProductInquiry> inquiries = productInquiryService.getInquiriesByPriority(priority);
        return ResponseEntity.ok(inquiries);
    }

    @GetMapping("/type/{inquiryType}")
    public ResponseEntity<List<ProductInquiry>> getInquiriesByType(@PathVariable String inquiryType) {
        List<ProductInquiry> inquiries = productInquiryService.getInquiriesByType(inquiryType);
        return ResponseEntity.ok(inquiries);
    }

    @GetMapping("/product/{productId}/status/{status}")
    public ResponseEntity<List<ProductInquiry>> getInquiriesByProductAndStatus(@PathVariable Long productId, @PathVariable String status) {
        List<ProductInquiry> inquiries = productInquiryService.getInquiriesByProductAndStatus(productId, status);
        return ResponseEntity.ok(inquiries);
    }

    @GetMapping("/user/{userId}/status/{status}")
    public ResponseEntity<List<ProductInquiry>> getInquiriesByUserAndStatus(@PathVariable Long userId, @PathVariable String status) {
        List<ProductInquiry> inquiries = productInquiryService.getInquiriesByUserAndStatus(userId, status);
        return ResponseEntity.ok(inquiries);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductInquiry>> getInquiriesByStatusAndPriority(
            @RequestParam String status,
            @RequestParam String priority) {
        List<ProductInquiry> inquiries = productInquiryService.getInquiriesByStatusAndPriority(status, priority);
        return ResponseEntity.ok(inquiries);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductInquiry> updateInquiry(@PathVariable Long id, @RequestBody ProductInquiry inquiryDetails) {
        ProductInquiry updatedInquiry = productInquiryService.updateInquiry(id, inquiryDetails);
        if (updatedInquiry != null) {
            return ResponseEntity.ok(updatedInquiry);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/answer")
    public ResponseEntity<ProductInquiry> answerInquiry(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        String response = requestBody.get("response");
        ProductInquiry answeredInquiry = productInquiryService.answerInquiry(id, response);
        if (answeredInquiry != null) {
            return ResponseEntity.ok(answeredInquiry);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<ProductInquiry> resolveInquiry(@PathVariable Long id) {
        ProductInquiry resolvedInquiry = productInquiryService.resolveInquiry(id);
        if (resolvedInquiry != null) {
            return ResponseEntity.ok(resolvedInquiry);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInquiry(@PathVariable Long id) {
        productInquiryService.deleteInquiry(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stats/overview")
    public ResponseEntity<Map<String, Long>> getInquiryStats() {
        long total = productInquiryService.getTotalInquiries();
        long pending = productInquiryService.getPendingInquiriesCount();
        long answered = productInquiryService.getAnsweredInquiriesCount();
        long resolved = productInquiryService.getResolvedInquiriesCount();
        return ResponseEntity.ok(Map.of("total", total, "pending", pending, "answered", answered, "resolved", resolved));
    }
}
