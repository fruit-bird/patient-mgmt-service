package org.dasai.patientmanagementservice.dtos;

import lombok.Data;

@Data
public class PatientDTO {
    private int id;
    private String firstName;
    private String email;
    private String description_diagnostic;
}
