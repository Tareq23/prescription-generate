package com.tareq23.prescription.controller;

import com.tareq23.prescription.dto.request.PrescriptionReportRequest;
import com.tareq23.prescription.service.PrescriptionReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PrescriptionReportController {

    private final PrescriptionReportService reportService;

    @PostMapping("/report")
    public ResponseEntity<?> report(@RequestBody PrescriptionReportRequest request){
        return ResponseEntity.ok(reportService.getReport(request.getStartDay(), request.getEndDay()));
    }

}
