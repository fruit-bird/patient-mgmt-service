package org.dasai.patientmanagementservice.web;

import emsi.ace.patientmanagementservice.stub.PatientServiceGrpc;
import emsi.ace.patientmanagementservice.stub.PatientServiceOuterClass;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.dasai.patientmanagementservice.entities.Patient;
import org.dasai.patientmanagementservice.mapper.PatientMapper;
import org.dasai.patientmanagementservice.respositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@GrpcService
public class PatientGrpcService extends PatientServiceGrpc.PatientServiceImplBase {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientMapper patientMapper;

    @Override
    public void getAllPatients(PatientServiceOuterClass.GetAllPatientsRequest request, StreamObserver<PatientServiceOuterClass.GetAllPatientsResponse> responseObserver){
        List<Patient> patientList = patientRepository.findAll();
        List<PatientServiceOuterClass.Patient> grpcPatients = patientList.stream()
                .map(patientMapper::fromPatientEntityToPatientGrpc).toList();
        PatientServiceOuterClass.GetAllPatientsResponse patientsResponse = PatientServiceOuterClass.GetAllPatientsResponse.newBuilder()
                .addAllPatients(grpcPatients)
                .build();

        responseObserver.onNext(patientsResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getPatientById(PatientServiceOuterClass.GetPatientByIdRequest request, StreamObserver<PatientServiceOuterClass.GetPatientByIdResponse> responseObserver){
        Patient patient = patientRepository.findById(request.getPatientId()).orElse(null);
        PatientServiceOuterClass.GetPatientByIdResponse response = PatientServiceOuterClass.GetPatientByIdResponse.newBuilder()
                .setPatient(patientMapper.fromPatientEntityToPatientGrpc(patient))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void savePatient(PatientServiceOuterClass.SavePatientRequest request, StreamObserver<PatientServiceOuterClass.SavePatientResponse> responseObserver){
        PatientServiceOuterClass.PatientRequest patientRequest = request.getPatient();
        Patient patient = patientMapper.fromGrpcPatientRequestToPatientEntity(patientRequest);
        Patient savedPatient = patientRepository.save(patient);
        PatientServiceOuterClass.SavePatientResponse response = PatientServiceOuterClass.SavePatientResponse.newBuilder()
                .setPatient(patientMapper.fromPatientEntityToPatientGrpc(savedPatient))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
//    @Override
//    public void updatePatient

}
