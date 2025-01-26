package com.tareq23.prescription.dto.response;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PrescriptionReportResponse {

    private LocalDate day;
    private Long count;

    public PrescriptionReportResponse(java.sql.Date prescriptionDate, long count) {
        this.day = prescriptionDate.toLocalDate();
        this.count = count;
    }
}
