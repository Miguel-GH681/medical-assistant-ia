package com.cosmos.medical_assistant.controller;

import com.cosmos.medical_assistant.dto.ChatRequest;
import com.cosmos.medical_assistant.service.AssistantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final AssistantService assistantService;

    @PostMapping
    public ResponseEntity<String> chat(
        @RequestBody ChatRequest chatRequest,
        @RequestHeader(value = "X-User-Id", defaultValue = "1") Long userId
    ){
        return ResponseEntity.ok(assistantService.chat(chatRequest.prompt(), chatRequest.model(), userId));
    }

    @PostMapping(value = "/stream", produces = "text/event-stream; charset=UTF-8")
    public Flux<String> chatStream(@RequestBody ChatRequest chatRequest){
        return assistantService.chatStream(chatRequest.prompt(), chatRequest.model());
    }

    @PostMapping("/explain")
    public ResponseEntity<String> explainCondition(@Valid @RequestBody ChatRequest chatRequest){
        return ResponseEntity.ok(assistantService.explainCondition(chatRequest.prompt(), chatRequest.model()));
    }

    @PostMapping("/symptoms")
    public ResponseEntity<String> analyzeSymptoms(@Valid @RequestBody ChatRequest chatRequest){
        return ResponseEntity.ok(assistantService.analyzeSymptoms(chatRequest.prompt(), chatRequest.model()));
    }

    @PostMapping("/diagnose")
    public ResponseEntity<String> diagnoseWithReasoning(@Valid @RequestBody ChatRequest chatRequest){
        return ResponseEntity.ok(assistantService.diagnoseWithReasoning(chatRequest.prompt(), chatRequest.model()));
    }

    @PostMapping("/consultation")
    public ResponseEntity<String> consultation(@Valid @RequestBody ChatRequest chatRequest){
        return ResponseEntity.ok(assistantService.consult(chatRequest.prompt(), chatRequest.model()));
    }
}
