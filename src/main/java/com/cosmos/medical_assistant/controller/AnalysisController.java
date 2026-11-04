package com.cosmos.medical_assistant.controller;

import com.cosmos.medical_assistant.dto.ChatRequest;
import com.cosmos.medical_assistant.dto.analysis.ConditionSummary;
import com.cosmos.medical_assistant.dto.analysis.SymptomAnalysis;
import com.cosmos.medical_assistant.service.AnalysisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/analysis")
@RequiredArgsConstructor
public class AnalysisController {

    private final AnalysisService analysisService;

    @PostMapping("/condition")
    public ResponseEntity<ConditionSummary> analyzeCondition(@Valid @RequestBody ChatRequest request){
        return ResponseEntity.ok(analysisService.conditionSummary(request.prompt(), request.model()));
    }

    @PostMapping("/conditions")
    public ResponseEntity<List<ConditionSummary>> analyzeConditions(@Valid @RequestBody ChatRequest request){
        return ResponseEntity.ok(analysisService.listRelatedConditions(request.prompt(), request.model()));
    }

    @PostMapping("/symptoms")
    public ResponseEntity<SymptomAnalysis> analyzeSymptoms(@Valid @RequestBody ChatRequest request){
        return ResponseEntity.ok(analysisService.symptomsAnalysis(request.prompt(), request.model()));
    }
}
