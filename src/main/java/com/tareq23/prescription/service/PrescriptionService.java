package com.tareq23.prescription.service;

import com.tareq23.prescription.dto.request.PrescriptionRequest;
import com.tareq23.prescription.entity.Prescription;
import org.springframework.data.domain.Page;

public interface PrescriptionService {


    boolean addPrescription(PrescriptionRequest request);

    boolean editPrescription(PrescriptionRequest request, Long id);

    boolean deletePrescription(Long id);

    Page<Prescription> getAllPrescription(int page, int size);

}
