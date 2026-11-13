package com.cosmos.medical_assistant.service;

import com.cosmos.medical_assistant.dto.DoctorInfo;

import java.util.List;

public interface DoctorService {
    List<DoctorInfo> searchDoctors(String query);
}
