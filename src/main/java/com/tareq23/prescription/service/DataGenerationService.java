package com.tareq23.prescription.service;

import com.tareq23.prescription.entity.Prescription;
import com.tareq23.prescription.entity.Users;
import com.tareq23.prescription.enums.Gender;
import com.tareq23.prescription.repository.PrescriptionRepository;
import com.tareq23.prescription.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DataGenerationService {

    private final UserRepository usersRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String[] FIRST_NAMES = {
            "John", "Jane", "Michael", "Sarah", "David", "Emma", "James", "Lisa",
            "Robert", "Maria", "William", "Patricia", "Richard", "Jennifer", "Thomas",
            "Linda", "Charles", "Elizabeth", "Daniel", "Susan", "Matthew", "Jessica",
            "Anthony", "Karen", "Mark", "Nancy", "Donald", "Betty", "Steven", "Helen"
    };

    private static final String[] LAST_NAMES = {
            "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller",
            "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez",
            "Wilson", "Anderson", "Thomas", "Taylor", "Moore", "Jackson", "Martin"
    };

    private static final String[] DIAGNOSES = {
            "Hypertension", "Type 2 Diabetes", "Upper Respiratory Infection",
            "Urinary Tract Infection", "Acute Bronchitis", "Allergic Rhinitis",
            "Migraine", "Lower Back Pain", "Osteoarthritis", "Anxiety Disorder",
            "Depression", "Asthma", "Gastroenteritis", "Sinusitis", "Conjunctivitis"
    };

    private static final String[] MEDICINES_LIST = {
            "Amoxicillin 500mg", "Lisinopril 10mg", "Metformin 850mg",
            "Ibuprofen 400mg", "Azithromycin 250mg", "Albuterol Inhaler",
            "Omeprazole 20mg", "Atorvastatin 20mg", "Amlodipine 5mg",
            "Paracetamol 500mg", "Cetirizine 10mg", "Prednisone 20mg"
    };

    private static final Random random = new Random();

    public void generateUsersAndPrescriptions(int numberOfUsers) {
        List<Users> users = new ArrayList<>();
        List<Prescription> allPrescriptions = new ArrayList<>();

        System.out.println("Starting generation of " + numberOfUsers + " users and their prescriptions...");

        for (int i = 0; i < numberOfUsers; i++) {
            // Create user
            Users user = createRandomUser(i);
            users.add(user);

            // Create 1-5 prescriptions per user
            int prescriptionCount = random.nextInt(5) + 1;
            for (int j = 0; j < prescriptionCount; j++) {
                Prescription prescription = createRandomPrescription(user, j);
                allPrescriptions.add(prescription);
            }

            // Progress indicator
            if ((i + 1) % 1000 == 0) {
                System.out.println("Generated " + (i + 1) + " users with their prescriptions...");
            }
        }

        // Batch save users
        System.out.println("Saving " + numberOfUsers + " users to database...");
        usersRepository.saveAll(users);

        // Batch save prescriptions
        System.out.println("Saving " + allPrescriptions.size() + " prescriptions to database...");
        prescriptionRepository.saveAll(allPrescriptions);

        System.out.println("Data loading completed! Total users: " + numberOfUsers +
                ", Total prescriptions: " + allPrescriptions.size());
    }

    private Users createRandomUser(int index) {
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        String email = firstName.toLowerCase() + "." + lastName.toLowerCase() +
                index + "@example.com";

        return Users.builder()
                .email(email)
                .password(passwordEncoder.encode("password123")) // Default password
                .build();
    }

    private Prescription createRandomPrescription(Users user, int prescriptionIndex) {
        LocalDate prescriptionDate = getRandomDate(
                LocalDate.now().minusYears(2),
                LocalDate.now()
        );

        String patientName = getRandomPatientName();
        Integer patientAge = random.nextInt(80) + 1; // Age 1-80
        Gender patientGender = random.nextBoolean() ? Gender.MALE : Gender.FEMALE;

        String diagnosis = DIAGNOSES[random.nextInt(DIAGNOSES.length)];
        String medicines = generateRandomMedicines();

        // Next visit date: 1-90 days from prescription date
        LocalDate nextVisitDate = random.nextBoolean() ?
                prescriptionDate.plusDays(random.nextInt(90) + 1) : null;

        return Prescription.builder()
                .prescriptionDate(prescriptionDate)
                .patientName(patientName)
                .patientAge(patientAge)
                .patientGender(patientGender)
                .diagnosis(diagnosis)
                .medicines(medicines)
                .nextVisitDate(nextVisitDate)
                .build();
    }

    private String getRandomPatientName() {
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        return firstName + " " + lastName;
    }

    private String generateRandomMedicines() {
        int medicineCount = random.nextInt(4) + 1; // 1-4 medicines
        Set<String> selectedMedicines = new HashSet<>();

        while (selectedMedicines.size() < medicineCount) {
            selectedMedicines.add(MEDICINES_LIST[random.nextInt(MEDICINES_LIST.length)]);
        }

        StringBuilder medicines = new StringBuilder();
        for (String medicine : selectedMedicines) {
            String dosage = getRandomDosage(medicine);
            int duration = random.nextInt(14) + 3; // 3-14 days
            medicines.append("- ").append(medicine)
                    .append(", ").append(dosage)
                    .append(", for ").append(duration).append(" days\n");
        }

        return medicines.toString();
    }

    private String getRandomDosage(String medicine) {
        if (medicine.contains("Inhaler")) {
            return "2 puffs twice daily";
        } else if (medicine.contains("mg")) {
            String[] dosages = {"Once daily", "Twice daily", "Three times daily"};
            return dosages[random.nextInt(dosages.length)];
        }
        return "As directed";
    }

    private LocalDate getRandomDate(LocalDate startInclusive, LocalDate endInclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endInclusive.toEpochDay();
        long randomDay = startEpochDay + random.nextInt((int) (endEpochDay - startEpochDay + 1));
        return LocalDate.ofEpochDay(randomDay);
    }
}
