package com.cosmos.medical_assistant.service;

import com.cosmos.medical_assistant.config.ClientResolver;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssistantServiceImp implements AssistantService {

    private PromptTemplate explainConditionTemplate;
    private PromptTemplate symptomAnalysisTemplate;
    private PromptTemplate diagnoseWithReasoningTemplate;
    private PromptTemplate consultationTemplate;
    private ClientResolver clientResolver;

    @Value("classpath:prompts/explain-condition.st")
    private Resource explainConditionPrompt;

    @Value("classpath:prompts/symptom-analysis.st")
    private Resource symptomAnalysisPrompt;

    @Value("classpath:prompts/diagnosis-cot.st")
    private Resource diagnoseWithReasoningPrompt;

    @Value("classpath:prompts/consultation.st")
    private Resource consultationPrompt;

    @PostConstruct
    void init(){
        explainConditionTemplate = new PromptTemplate(explainConditionPrompt);
        symptomAnalysisTemplate = new PromptTemplate(symptomAnalysisPrompt);
        diagnoseWithReasoningTemplate = new PromptTemplate(diagnoseWithReasoningPrompt);
        consultationTemplate = new PromptTemplate(consultationPrompt);
    }

    @Override
    public String chat(String prompt, String model) {
        log.info("Chat request - modelo {}", model);
        return clientResolver.resolve(model)
            .prompt(prompt)
            .call()
            .content();
    }

    @Override
    public Flux<String> chatStream(String prompt, String model) {
        log.info("Chat stream request - modelo {}", model);
        return clientResolver.resolve(model)
            .prompt(prompt)
            .stream()
            .content();
    }

    @Override
    public String explainCondition(String condition, String model) {
        log.info("Explain request - condición: {}, modelo: {}", condition, model);
        String message = explainConditionTemplate.render(Map.of("condicion", condition));

        return clientResolver.resolve(model)
            .prompt(message)
            .call()
            .content();
    }

    @Override
    public String analyzeSymptoms(String symptoms, String model) {
        log.info("Analysis symptom request - síntomas: {}, modelo: {}", symptoms, model);
        String message = symptomAnalysisTemplate.render(Map.of("symptoms", symptoms));

        return clientResolver.resolve(model)
            .prompt(message)
            .call()
            .content();
    }

    @Override
    public String diagnoseWithReasoning(String symptoms, String model) {
        log.info("Diagnose with reasoning request - síntomas: {}, modelo: {}", symptoms, model);
        String message = diagnoseWithReasoningTemplate.render(Map.of("sintomas", symptoms));

        return clientResolver.resolve(model)
            .prompt(message)
            .call()
            .content();
    }

    @Override
    public String consult(String query, String model) {
        log.info("Diagnose with consult - síntomas: {}, modelo: {}", query, model);
        String message = consultationTemplate.render(Map.of("consulta", query));

        return clientResolver.resolve(model)
            .prompt(message)
            .call()
            .content();
    }
}
