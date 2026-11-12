package com.cosmos.medical_assistant.repository;

import com.cosmos.medical_assistant.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
