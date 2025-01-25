package com.tareq23.prescription.dto.request;

import com.tareq23.prescription.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionRequest {

    @NotNull
    private LocalDate prescriptionDate;

    @NotNull
    @Min(3)
    @Max(50)
    private String patientName;

    @NotNull
    @Min(0)
    @Max(120)
    private Integer patientAge;

    @NotNull
    @Embedded
    private Gender patientGender;

    private String diagnosis;

    private String medicines;

    private LocalDate nextVisitDate;
}
