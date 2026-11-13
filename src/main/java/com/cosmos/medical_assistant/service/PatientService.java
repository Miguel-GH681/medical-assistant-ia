package com.cosmos.medical_assistant.service;

import com.cosmos.medical_assistant.dto.PatientInfo;

public interface PatientService {
    PatientInfo getPatientInfo(Long patientId);
}
