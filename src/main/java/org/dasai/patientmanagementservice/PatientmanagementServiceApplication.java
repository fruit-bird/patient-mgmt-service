package org.dasai.patientmanagementservice;

import org.dasai.patientmanagementservice.entities.Patient;
import org.dasai.patientmanagementservice.respositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PatientmanagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientmanagementServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(Patient.builder().firstName("bruh1").email("bruh1@gmail.com").description_diagnostic("ECG Scan").build());
            patientRepository.save(Patient.builder().firstName("bruh2").email("bruh2@gmail.com").description_diagnostic("EEG Scan").build());
            patientRepository.save(Patient.builder().firstName("bruh3").email("bruh3@gmail.com").description_diagnostic("X-Ray Scan").build());
        };
    }
}
