package org.boumya.patientmanagementservice.web;

import lombok.AllArgsConstructor;
import org.boumya.patientmanagementservice.entities.Patient;
import org.boumya.patientmanagementservice.respositories.PatientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PatientRestController {
    private PatientRepository patientRepository;

    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/patients/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientRepository.findById(id).get();
    }

    @PostMapping("/patients")
    public Patient createPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }
}
