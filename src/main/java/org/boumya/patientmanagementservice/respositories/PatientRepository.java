package org.boumya.patientmanagementservice.respositories;

import org.boumya.patientmanagementservice.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
