package com.tareq23.prescription.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tareq23.prescription.entity.Prescription;
import com.tareq23.prescription.repository.PrescriptionRepository;
import com.tareq23.prescription.service.impl.PrescriptionServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    private final PrescriptionRepository prescriptionRepository;
    private final ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);
    @Transactional
    public void loadPrescriptionsFromJson() {
        try {
//            InputStream inputStream = new ClassPathResource("data/prescription.json").getInputStream();
            InputStream inputStream = new FileInputStream(new File("src/main/resources/static/data/prescription.json"));
            List<Prescription> prescriptions = objectMapper.readValue(inputStream, new TypeReference<List<Prescription>>() {
            });

            prescriptionRepository.saveAll(prescriptions);
            logger.info("Prescriptions saved successfully!");

        } catch (IOException e) {
            throw new RuntimeException("Error loading prescriptions JSON "+e.getMessage());
        }
    }

}
