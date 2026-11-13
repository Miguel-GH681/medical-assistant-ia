package com.cosmos.medical_assistant.service;

import com.cosmos.medical_assistant.dto.PatientInfo;
import com.cosmos.medical_assistant.model.Patient;
import com.cosmos.medical_assistant.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService{

    private final PatientRepository patientRepository;

    @Override
    public PatientInfo getPatientInfo(Long patientId) {
        return patientRepository
            .findById(patientId)
            .map(this::toPatientInfo)
            .orElse(null);
    }

    private PatientInfo toPatientInfo(Patient patient){
        return new PatientInfo(
            patient.getFirstName(),
            patient.getLastName(),
            patient.getDateOfBirth().toString(),
            patient.getAllergies(),
            patient.getConditions()
        );
    }
}
