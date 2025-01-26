package com.tareq23.prescription.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionReportRequest {
    private LocalDate startDay;
    private LocalDate endDay;
}
