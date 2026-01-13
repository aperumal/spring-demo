package com.example.demo.repository;

import com.example.demo.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    List<Inquiry> findByUserId(Long userId);
    List<Inquiry> findByStatus(String status);
    List<Inquiry> findByUserIdAndStatus(Long userId, String status);
    List<Inquiry> findByPriority(String priority);
    List<Inquiry> findByInquiryType(String inquiryType);
}
