package com.cosmos.medical_assistant.service;

import com.cosmos.medical_assistant.dto.DrugInfo;

public interface DrugInfoService {
    DrugInfo getDrugInfo(String drugName);
}
