package org.dasai.patientmanagementservice.respositories;

import org.dasai.patientmanagementservice.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
