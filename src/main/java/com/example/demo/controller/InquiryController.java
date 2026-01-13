package com.example.demo.controller;

import com.example.demo.entity.Inquiry;
import com.example.demo.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inquiries")
@CrossOrigin(origins = "http://localhost:4200")
public class InquiryController {
    @Autowired
    private InquiryService inquiryService;

    @PostMapping
    public ResponseEntity<Inquiry> createInquiry(@RequestBody Inquiry inquiry) {
        Inquiry createdInquiry = inquiryService.createInquiry(inquiry);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInquiry);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inquiry> getInquiryById(@PathVariable Long id) {
        return inquiryService.getInquiryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Inquiry>> getAllInquiries() {
        List<Inquiry> inquiries = inquiryService.getAllInquiries();
        return ResponseEntity.ok(inquiries);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Inquiry>> getInquiriesByUserId(@PathVariable Long userId) {
        List<Inquiry> inquiries = inquiryService.getInquiriesByUserId(userId);
        return ResponseEntity.ok(inquiries);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Inquiry>> getInquiriesByStatus(@PathVariable String status) {
        List<Inquiry> inquiries = inquiryService.getInquiriesByStatus(status);
        return ResponseEntity.ok(inquiries);
    }

    @GetMapping("/status/open")
    public ResponseEntity<List<Inquiry>> getOpenInquiries() {
        List<Inquiry> inquiries = inquiryService.getOpenInquiries();
        return ResponseEntity.ok(inquiries);
    }

    @GetMapping("/status/closed")
    public ResponseEntity<List<Inquiry>> getClosedInquiries() {
        List<Inquiry> inquiries = inquiryService.getClosedInquiries();
        return ResponseEntity.ok(inquiries);
    }

    @GetMapping("/user/{userId}/status/{status}")
    public ResponseEntity<List<Inquiry>> getInquiriesByUserAndStatus(@PathVariable Long userId, @PathVariable String status) {
        List<Inquiry> inquiries = inquiryService.getInquiriesByUserIdAndStatus(userId, status);
        return ResponseEntity.ok(inquiries);
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Inquiry>> getInquiriesByPriority(@PathVariable String priority) {
        List<Inquiry> inquiries = inquiryService.getInquiriesByPriority(priority);
        return ResponseEntity.ok(inquiries);
    }

    @GetMapping("/type/{inquiryType}")
    public ResponseEntity<List<Inquiry>> getInquiriesByType(@PathVariable String inquiryType) {
        List<Inquiry> inquiries = inquiryService.getInquiriesByType(inquiryType);
        return ResponseEntity.ok(inquiries);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inquiry> updateInquiry(@PathVariable Long id, @RequestBody Inquiry inquiryDetails) {
        Inquiry updatedInquiry = inquiryService.updateInquiry(id, inquiryDetails);
        if (updatedInquiry != null) {
            return ResponseEntity.ok(updatedInquiry);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<Inquiry> resolveInquiry(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        String response = requestBody.get("response");
        Inquiry resolvedInquiry = inquiryService.resolveInquiry(id, response);
        if (resolvedInquiry != null) {
            return ResponseEntity.ok(resolvedInquiry);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInquiry(@PathVariable Long id) {
        inquiryService.deleteInquiry(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stats/total")
    public ResponseEntity<Map<String, Long>> getTotalInquiries() {
        long total = inquiryService.getTotalInquiries();
        long open = inquiryService.getOpenInquiriesCount();
        return ResponseEntity.ok(Map.of("total", total, "open", open));
    }
}
