package com.tareq23.prescription.dto.response;

import com.tareq23.prescription.enums.Gender;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionResponse {


    private LocalDate prescriptionDate;
    private String patientName;

    private Integer patientAge;
    private Gender patientGender;

    private String diagnosis;

    private String medicines;

    private LocalDate nextVisitDate;


}
