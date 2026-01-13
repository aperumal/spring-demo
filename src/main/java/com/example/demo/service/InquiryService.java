package com.example.demo.service;

import com.example.demo.entity.Inquiry;
import com.example.demo.entity.User;
import com.example.demo.repository.InquiryRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InquiryService {
    @Autowired
    private InquiryRepository inquiryRepository;

    @Autowired
    private UserRepository userRepository;

    public Inquiry createInquiry(Inquiry inquiry) {
        return inquiryRepository.save(inquiry);
    }

    public Optional<Inquiry> getInquiryById(Long id) {
        return inquiryRepository.findById(id);
    }

    public List<Inquiry> getAllInquiries() {
        return inquiryRepository.findAll();
    }

    public List<Inquiry> getInquiriesByUserId(Long userId) {
        return inquiryRepository.findByUserId(userId);
    }

    public List<Inquiry> getInquiriesByStatus(String status) {
        return inquiryRepository.findByStatus(status);
    }

    public List<Inquiry> getOpenInquiries() {
        return inquiryRepository.findByStatus("OPEN");
    }

    public List<Inquiry> getClosedInquiries() {
        return inquiryRepository.findByStatus("CLOSED");
    }

    public List<Inquiry> getInquiriesByUserIdAndStatus(Long userId, String status) {
        return inquiryRepository.findByUserIdAndStatus(userId, status);
    }

    public List<Inquiry> getInquiriesByPriority(String priority) {
        return inquiryRepository.findByPriority(priority);
    }

    public List<Inquiry> getInquiriesByType(String inquiryType) {
        return inquiryRepository.findByInquiryType(inquiryType);
    }

    public Inquiry updateInquiry(Long id, Inquiry inquiryDetails) {
        return inquiryRepository.findById(id).map(inquiry -> {
            if (inquiryDetails.getSubject() != null) inquiry.setSubject(inquiryDetails.getSubject());
            if (inquiryDetails.getDescription() != null) inquiry.setDescription(inquiryDetails.getDescription());
            if (inquiryDetails.getInquiryType() != null) inquiry.setInquiryType(inquiryDetails.getInquiryType());
            if (inquiryDetails.getStatus() != null) inquiry.setStatus(inquiryDetails.getStatus());
            if (inquiryDetails.getPriority() != null) inquiry.setPriority(inquiryDetails.getPriority());
            if (inquiryDetails.getResponse() != null) inquiry.setResponse(inquiryDetails.getResponse());
            return inquiryRepository.save(inquiry);
        }).orElse(null);
    }

    public Inquiry resolveInquiry(Long id, String response) {
        return inquiryRepository.findById(id).map(inquiry -> {
            inquiry.setStatus("CLOSED");
            inquiry.setResponse(response);
            inquiry.setResolvedAt(LocalDateTime.now());
            return inquiryRepository.save(inquiry);
        }).orElse(null);
    }

    public void deleteInquiry(Long id) {
        inquiryRepository.deleteById(id);
    }

    public long getTotalInquiries() {
        return inquiryRepository.count();
    }

    public long getOpenInquiriesCount() {
        return getOpenInquiries().size();
    }
}
