package com.cosmos.medical_assistant.service;

import com.cosmos.medical_assistant.dto.analysis.ConditionSummary;
import com.cosmos.medical_assistant.dto.analysis.QueryClassification;
import com.cosmos.medical_assistant.dto.analysis.SymptomAnalysis;

import java.util.List;

public interface AnalysisService {
    ConditionSummary conditionSummary(String condition, String model);

    List<ConditionSummary> listRelatedConditions(String symptoms, String model);

    SymptomAnalysis symptomsAnalysis(String symptoms, String model);

    QueryClassification queryClassification(String query, String model);
}
