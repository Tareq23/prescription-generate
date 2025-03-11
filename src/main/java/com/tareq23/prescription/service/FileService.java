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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    private final PrescriptionRepository prescriptionRepository;
    private final ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Value("${resources.static.data.prescription}")
    private String fileLocation;

    @Transactional
    public void loadPrescriptionsFromJson() throws IOException {
         try{
            Path path = new ClassPathResource(this.fileLocation).getFile().toPath();
//            InputStream inputStream = new FileInputStream(new File(path));
            List<Prescription> prescriptions = objectMapper.readValue(Files.readString(path), new TypeReference<List<Prescription>>() {
            });

            prescriptionRepository.saveAll(prescriptions);
            logger.info("Prescriptions saved successfully!");

        } catch (IOException e) {
            throw new RuntimeException("Error loading prescriptions JSON "+e.getMessage());
        }

//        try{
//
//            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(this.fileLocation);
//            if (inputStream == null) {
//                throw new RuntimeException("Prescription JSON file not found in classpath!");
//            }
//
//            // Read JSON from InputStream
//            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
//            List<Prescription> prescriptions = new ObjectMapper().readValue(reader, new TypeReference<List<Prescription>>() {});
//
//            prescriptionRepository.saveAll(prescriptions);
//
//            logger.info("Prescriptions saved successfully!");
//        }
//        catch (IOException e){
//            throw new RuntimeException("Error loading prescriptions JSON: " + e.getMessage());
//        }
    }

}
