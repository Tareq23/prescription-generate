package com.tareq23.prescription.service.impl;

import com.tareq23.prescription.dto.request.PrescriptionRequest;
import com.tareq23.prescription.entity.Prescription;
import com.tareq23.prescription.repository.PrescriptionRepository;
import com.tareq23.prescription.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {

    private static final Logger logger = LoggerFactory.getLogger(PrescriptionServiceImpl.class);
    private final ModelMapper mapper;
    private final PrescriptionRepository prescriptionRepository;

    @Override
    public boolean addPrescription(PrescriptionRequest request) {
        try{
            Prescription prescription = convertToEntity(request);
            logger.info("{}", prescription.toString());
            prescriptionRepository.save(prescription);
        }catch(Exception e){
            logger.error("Prescription entry error: {}", e.getMessage());
        }
        return true;
    }

    @Override
    public boolean editPrescription(PrescriptionRequest request, Long id) {
        try{
            Optional<Prescription> optionalPrescription = prescriptionRepository.findById(id);
            if(optionalPrescription.isPresent()){
                Prescription prescription = optionalPrescription.get();
                mapper.map(request, prescription);
                prescriptionRepository.save(prescription);
            }
            else{
                throw new IllegalArgumentException("Prescription doesn't exists");
            }
        }catch(Exception e){
            logger.error("Prescription entry update error: {}", e.getMessage());
        }
        return true;
    }

    @Override
    public boolean deletePrescription(Long id) {
        try{
            prescriptionRepository.deleteById(id);
        }catch (Exception e){
            logger.error("Prescription delete error: {}", e.getMessage());
        }
        return true;
    }

    @Override
    public Page<Prescription> getAllPrescription(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return prescriptionRepository.findAll(pageable);
    }

    public Optional<Prescription> getById(Long id){
        return prescriptionRepository.findById(id);
    }


    private Prescription convertToEntity(PrescriptionRequest request) {
        return mapper.map(request, Prescription.class);
    }

}
