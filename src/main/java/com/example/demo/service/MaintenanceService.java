package com.example.demo.service;

import com.example.demo.entity.Maintenance;
import com.example.demo.repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceService {
    @Autowired
    private MaintenanceRepository maintenanceRepository;

    public Maintenance createMaintenance(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    public Optional<Maintenance> getMaintenanceById(Long id) {
        return maintenanceRepository.findById(id);
    }

    public List<Maintenance> getAllMaintenance() {
        return maintenanceRepository.findAll();
    }

    public List<Maintenance> getMaintenanceByStatus(String status) {
        return maintenanceRepository.findByStatus(status);
    }

    public List<Maintenance> getScheduledMaintenance() {
        return maintenanceRepository.findByStatusOrderByScheduledDateAsc("SCHEDULED");
    }

    public List<Maintenance> getCompletedMaintenance() {
        return maintenanceRepository.findByStatus("COMPLETED");
    }

    public List<Maintenance> getInProgressMaintenance() {
        return maintenanceRepository.findByStatus("IN_PROGRESS");
    }

    public List<Maintenance> getMaintenanceByType(String maintenanceType) {
        return maintenanceRepository.findByMaintenanceType(maintenanceType);
    }

    public List<Maintenance> getMaintenanceByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return maintenanceRepository.findByScheduledDateBetween(startDate, endDate);
    }

    public Maintenance updateMaintenance(Long id, Maintenance maintenanceDetails) {
        return maintenanceRepository.findById(id).map(maintenance -> {
            if (maintenanceDetails.getTitle() != null) maintenance.setTitle(maintenanceDetails.getTitle());
            if (maintenanceDetails.getDescription() != null) maintenance.setDescription(maintenanceDetails.getDescription());
            if (maintenanceDetails.getMaintenanceType() != null) maintenance.setMaintenanceType(maintenanceDetails.getMaintenanceType());
            if (maintenanceDetails.getStatus() != null) maintenance.setStatus(maintenanceDetails.getStatus());
            if (maintenanceDetails.getScheduledDate() != null) maintenance.setScheduledDate(maintenanceDetails.getScheduledDate());
            if (maintenanceDetails.getEstimatedDuration() != null) maintenance.setEstimatedDuration(maintenanceDetails.getEstimatedDuration());
            if (maintenanceDetails.getAffectedSystems() != null) maintenance.setAffectedSystems(maintenanceDetails.getAffectedSystems());
            if (maintenanceDetails.getNotes() != null) maintenance.setNotes(maintenanceDetails.getNotes());
            return maintenanceRepository.save(maintenance);
        }).orElse(null);
    }

    public Maintenance completeMaintenance(Long id, Integer actualDuration) {
        return maintenanceRepository.findById(id).map(maintenance -> {
            maintenance.setStatus("COMPLETED");
            maintenance.setCompletedDate(LocalDateTime.now());
            if (actualDuration != null) maintenance.setActualDuration(actualDuration);
            return maintenanceRepository.save(maintenance);
        }).orElse(null);
    }

    public Maintenance startMaintenance(Long id) {
        return maintenanceRepository.findById(id).map(maintenance -> {
            maintenance.setStatus("IN_PROGRESS");
            return maintenanceRepository.save(maintenance);
        }).orElse(null);
    }

    public void deleteMaintenance(Long id) {
        maintenanceRepository.deleteById(id);
    }

    public long getTotalMaintenance() {
        return maintenanceRepository.count();
    }

    public long getScheduledCount() {
        return getScheduledMaintenance().size();
    }

    public long getCompletedCount() {
        return getCompletedMaintenance().size();
    }
}
