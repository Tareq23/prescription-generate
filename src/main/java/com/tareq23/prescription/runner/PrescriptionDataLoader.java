package com.tareq23.prescription.runner;

import com.tareq23.prescription.repository.PrescriptionRepository;
import com.tareq23.prescription.repository.UserRepository;
import com.tareq23.prescription.service.DataGenerationService;
import com.tareq23.prescription.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrescriptionDataLoader implements CommandLineRunner {

    private final DataGenerationService dataGenerationService;
    private final UserRepository usersRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists to avoid duplicate loading
        if (usersRepository.count() == 0) {
            System.out.println("No existing users found. Loading sample data...");
            long startTime = System.currentTimeMillis();

            // Generate 10,000 users with their prescriptions
            dataGenerationService.generateUsersAndPrescriptions(50);

            long endTime = System.currentTimeMillis();
            System.out.println("Data loading completed in " + (endTime - startTime) / 1000 + " seconds");
        } else {
            System.out.println("Data already exists. Skipping data loading. Total users: " +
                    usersRepository.count());
        }
    }
}
