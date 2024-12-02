package org.dasai.patientmanagementservice.web;

import lombok.AllArgsConstructor;
import org.dasai.patientmanagementservice.dtos.PatientDTO;
import org.dasai.patientmanagementservice.entities.Patient;
import org.dasai.patientmanagementservice.mapper.PatientMapper;
import org.dasai.patientmanagementservice.respositories.PatientRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientGraphQLController {
    private PatientRepository patientRepository;
    private PatientMapper patientMapper;

    @QueryMapping
    public List<Patient> allPatients() {
        return patientRepository.findAll();
    }

    @QueryMapping
    public Patient patientById(@Argument Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) throw new RuntimeException(String.format("Patient with id %s not found", id));
        return patient;
    }

    @MutationMapping
    public Patient savePatient(@Argument PatientDTO patientDTO) {
        Patient patient = patientMapper.fromPatientDtoToPatientEntity(patientDTO);
        return patientRepository.save(patient);
    }
}
