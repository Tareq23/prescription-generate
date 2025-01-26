package com.tareq23.prescription.service;

import com.tareq23.prescription.dto.response.PrescriptionReportResponse;
import com.tareq23.prescription.entity.Prescription;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface PrescriptionReportService {

    List<?> getReport(LocalDate startDay, LocalDate endDay);

}
