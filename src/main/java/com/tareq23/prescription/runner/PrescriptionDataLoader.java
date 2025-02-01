package com.tareq23.prescription.runner;

import com.tareq23.prescription.repository.PrescriptionRepository;
import com.tareq23.prescription.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrescriptionDataLoader implements CommandLineRunner {


    private final FileService fileService;

    @Override
    public void run(String... args) throws Exception {
        fileService.loadPrescriptionsFromJson();
    }
}
