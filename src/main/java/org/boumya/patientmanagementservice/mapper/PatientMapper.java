package org.boumya.patientmanagementservice.mapper;

import emsi.ace.patientmanagementservice.stub.PatientServiceGrpc;
import emsi.ace.patientmanagementservice.stub.PatientServiceOuterClass;
import org.boumya.patientmanagementservice.dtos.PatientDTO;
import org.boumya.patientmanagementservice.entities.Patient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public Patient fromPatientDtoToPatientEntity(PatientDTO patientDTO){
        return modelMapper.map(patientDTO, Patient.class);
    }

    public PatientServiceOuterClass.Patient fromPatientEntityToPatientGrpc(Patient patient) {
        return PatientServiceOuterClass.Patient.newBuilder().setId(patient.getId())
                .setFirstName(patient.getFirstName())
                .setEmail(patient.getEmail())
                .build();
    }


    public Patient fromGrpcPatientRequestToPatientEntity(PatientServiceOuterClass.PatientRequest patientRequest) {
        Patient patient = new Patient();
        patient.setFirstName(patientRequest.getFirstName());
        patient.setEmail(patientRequest.getEmail());
        return patient;
    }
}
