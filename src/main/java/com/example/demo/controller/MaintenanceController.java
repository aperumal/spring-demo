package com.example.demo.controller;

import com.example.demo.entity.Maintenance;
import com.example.demo.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/maintenance")
@CrossOrigin(origins = "http://localhost:4200")
public class MaintenanceController {
    @Autowired
    private MaintenanceService maintenanceService;

    @PostMapping
    public ResponseEntity<Maintenance> createMaintenance(@RequestBody Maintenance maintenance) {
        Maintenance createdMaintenance = maintenanceService.createMaintenance(maintenance);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMaintenance);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getMaintenanceById(@PathVariable Long id) {
        return maintenanceService.getMaintenanceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Maintenance>> getAllMaintenance() {
        List<Maintenance> maintenance = maintenanceService.getAllMaintenance();
        return ResponseEntity.ok(maintenance);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Maintenance>> getMaintenanceByStatus(@PathVariable String status) {
        List<Maintenance> maintenance = maintenanceService.getMaintenanceByStatus(status);
        return ResponseEntity.ok(maintenance);
    }

    @GetMapping("/status/scheduled")
    public ResponseEntity<List<Maintenance>> getScheduledMaintenance() {
        List<Maintenance> maintenance = maintenanceService.getScheduledMaintenance();
        return ResponseEntity.ok(maintenance);
    }

    @GetMapping("/status/in-progress")
    public ResponseEntity<List<Maintenance>> getInProgressMaintenance() {
        List<Maintenance> maintenance = maintenanceService.getInProgressMaintenance();
        return ResponseEntity.ok(maintenance);
    }

    @GetMapping("/status/completed")
    public ResponseEntity<List<Maintenance>> getCompletedMaintenance() {
        List<Maintenance> maintenance = maintenanceService.getCompletedMaintenance();
        return ResponseEntity.ok(maintenance);
    }

    @GetMapping("/type/{maintenanceType}")
    public ResponseEntity<List<Maintenance>> getMaintenanceByType(@PathVariable String maintenanceType) {
        List<Maintenance> maintenance = maintenanceService.getMaintenanceByType(maintenanceType);
        return ResponseEntity.ok(maintenance);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Maintenance>> getMaintenanceByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        List<Maintenance> maintenance = maintenanceService.getMaintenanceByDateRange(start, end);
        return ResponseEntity.ok(maintenance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> updateMaintenance(@PathVariable Long id, @RequestBody Maintenance maintenanceDetails) {
        Maintenance updatedMaintenance = maintenanceService.updateMaintenance(id, maintenanceDetails);
        if (updatedMaintenance != null) {
            return ResponseEntity.ok(updatedMaintenance);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/start")
    public ResponseEntity<Maintenance> startMaintenance(@PathVariable Long id) {
        Maintenance startedMaintenance = maintenanceService.startMaintenance(id);
        if (startedMaintenance != null) {
            return ResponseEntity.ok(startedMaintenance);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Maintenance> completeMaintenance(@PathVariable Long id, @RequestBody Map<String, Integer> requestBody) {
        Integer actualDuration = requestBody.get("actualDuration");
        Maintenance completedMaintenance = maintenanceService.completeMaintenance(id, actualDuration);
        if (completedMaintenance != null) {
            return ResponseEntity.ok(completedMaintenance);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable Long id) {
        maintenanceService.deleteMaintenance(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stats/overview")
    public ResponseEntity<Map<String, Long>> getMaintenanceStats() {
        long total = maintenanceService.getTotalMaintenance();
        long scheduled = maintenanceService.getScheduledCount();
        long completed = maintenanceService.getCompletedCount();
        return ResponseEntity.ok(Map.of("total", total, "scheduled", scheduled, "completed", completed));
    }
}
