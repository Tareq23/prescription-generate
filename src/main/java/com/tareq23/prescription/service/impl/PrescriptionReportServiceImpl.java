package com.tareq23.prescription.service.impl;

import com.tareq23.prescription.dto.response.PrescriptionReportResponse;
import com.tareq23.prescription.entity.Prescription;
import com.tareq23.prescription.service.PrescriptionReportService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrescriptionReportServiceImpl implements PrescriptionReportService {

    private final EntityManager entityManager;


    @Override
    public List<PrescriptionReportResponse> getReport(LocalDate startDay, LocalDate endDay) {
        String sql = "SELECT PRESCRIPTION_DATE, COUNT(*) FROM PRESCRIPTION WHERE PRESCRIPTION_DATE BETWEEN :startDay AND :endDay GROUP BY PRESCRIPTION_DATE";
        Query query = entityManager.createNativeQuery(sql, PrescriptionReportResponse.class);
        query.setParameter("startDay", startDay);
        query.setParameter("endDay", endDay);
        return query.getResultList();
    }
}
