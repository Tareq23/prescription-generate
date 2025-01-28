package com.tareq23.prescription.controller;


import com.tareq23.prescription.dto.request.PrescriptionRequest;
import com.tareq23.prescription.repository.PrescriptionRepository;
import com.tareq23.prescription.service.PrescriptionService;
import com.tareq23.prescription.service.impl.PrescriptionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;
    @PostMapping("/prescription/add")
    @PreAuthorize("hasAuthority('USERS')")
    public ResponseEntity<?> addPrescription(@RequestBody PrescriptionRequest request) {
        return ResponseEntity.ok(prescriptionService.addPrescription(request));
    }

    @GetMapping("/prescription")
    public ResponseEntity<?> getAllPrescription(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return ResponseEntity.ok(prescriptionService.getAllPrescription(page, limit));
    }

    @GetMapping("/prescription/{id}")
    public ResponseEntity<?> getByIdForEdit(@PathVariable("id") Long id) {
        return ResponseEntity.ok(prescriptionService.getById(id));
    }

    @PutMapping("/prescription/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody PrescriptionRequest request) {
        return ResponseEntity.ok(prescriptionService.editPrescription(request, id));
    }

    @DeleteMapping("/prescription/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(prescriptionService.deletePrescription(id));
    }



}
