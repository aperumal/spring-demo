package com.example.demo.repository;

import com.example.demo.entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
    List<Maintenance> findByStatus(String status);
    List<Maintenance> findByMaintenanceType(String maintenanceType);
    List<Maintenance> findByScheduledDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Maintenance> findByStatusOrderByScheduledDateAsc(String status);
}
