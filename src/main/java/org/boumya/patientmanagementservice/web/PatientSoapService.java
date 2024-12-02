package org.boumya.patientmanagementservice.web;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.AllArgsConstructor;
import org.boumya.patientmanagementservice.dtos.PatientDTO;
import org.boumya.patientmanagementservice.entities.Patient;
import org.boumya.patientmanagementservice.mapper.PatientMapper;
import org.boumya.patientmanagementservice.respositories.PatientRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@WebService
public class PatientSoapService {
    private PatientRepository patientRepository;
    private PatientMapper patientMapper;

    public List<Patient> patientList(){return patientRepository.findAll();}

    @WebMethod
    public Patient patientById(@WebParam(name = "id") Long id){
        return patientRepository.findById(id).get();
    }

    @WebMethod
    public Patient addPatient(@WebParam(name = "patient") PatientDTO patientDTO){
        Patient p = patientMapper.fromPatientDtoToPatientEntity(patientDTO);
        return patientRepository.save(p);
    }

}
