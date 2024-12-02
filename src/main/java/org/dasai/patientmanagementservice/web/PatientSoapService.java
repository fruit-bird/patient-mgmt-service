package org.dasai.patientmanagementservice.web;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.AllArgsConstructor;
import org.dasai.patientmanagementservice.dtos.PatientDTO;
import org.dasai.patientmanagementservice.entities.Patient;
import org.dasai.patientmanagementservice.mapper.PatientMapper;
import org.dasai.patientmanagementservice.respositories.PatientRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@WebService
public class PatientSoapService {
    private PatientRepository patientRepository;
    private PatientMapper patientMapper;

    public List<Patient> patientList() {
        return patientRepository.findAll();
    }

    @WebMethod
    public Patient patientById(@WebParam(name = "id") Long id) {
        return patientRepository.findById(id).get();
    }

    @WebMethod
    public Patient addPatient(@WebParam(name = "patient") PatientDTO patientDTO) {
        Patient patient = patientMapper.fromPatientDtoToPatientEntity(patientDTO);
        return patientRepository.save(patient);
    }

}
