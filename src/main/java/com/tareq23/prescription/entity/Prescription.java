package com.tareq23.prescription.entity;

import com.tareq23.prescription.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRESCRIPTION_DATE", nullable = false)
    private LocalDate prescriptionDate;

    @Column(name = "PATIENT_NAME", nullable = false)
    private String patientName;


//    @Embedded
    @Column(name = "PATIENT_AGE", nullable = false)
    private Integer patientAge;

    @Enumerated(EnumType.STRING)
    @Column(name = "PATIENT_GENDER", length = 20, nullable = false)
    private Gender patientGender;

    @Lob
    @Column(name = "DIAGNOSIS", nullable = true)
    private String diagnosis;

    @Lob
    @Column(name = "MEDICINES", nullable = true)
    private String medicines;

    @Column(name = "NEXT_VISIT_DATE", nullable = true)
    private LocalDate nextVisitDate;


}
