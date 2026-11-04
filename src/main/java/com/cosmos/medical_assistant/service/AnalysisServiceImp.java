package com.cosmos.medical_assistant.service;

import com.cosmos.medical_assistant.config.ClientResolver;
import com.cosmos.medical_assistant.dto.analysis.ConditionSummary;
import com.cosmos.medical_assistant.dto.analysis.PossibleCondition;
import com.cosmos.medical_assistant.dto.analysis.SymptomAnalysis;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnalysisServiceImp implements AnalysisService{

    private PromptTemplate structuredTemplate;

    @Value("classpath:prompts/structured-analysis.st")
    private Resource structuredPrompt;

    private final ClientResolver clientResolver;

    @PostConstruct
    void init(){
        structuredTemplate = new PromptTemplate(structuredPrompt);
    }

    @Override
    public ConditionSummary conditionSummary(String condition, String model) {
        return clientResolver.resolve(model)
            .prompt()
            .user("Proporciona un resumen médico sobre: " + condition)
            .call()
            .entity(ConditionSummary.class);
    }

    @Override
    public List<ConditionSummary> listRelatedConditions(String symptoms, String model) {
        log.info("Listado de condiciones relacionadas - modelo: {}", model);
        return clientResolver.resolve(model)
            .prompt()
            .user("Lista las 3 condiciones médicas más probables para estos síntomas: " + symptoms)
            .call()
            .entity(new ParameterizedTypeReference<List<ConditionSummary>>() {
            });
    }

    @Override
    public SymptomAnalysis symptomsAnalysis(String symptoms, String model) {
        log.info("Listado estructurado de condiciones relacionadas - modelo: {}", model);

        String message = structuredTemplate.render(Map.of("sintomas", symptoms));

        return clientResolver.resolve(model)
            .prompt()
            .user(message)
            .call()
            .entity(SymptomAnalysis.class);
    }
}
