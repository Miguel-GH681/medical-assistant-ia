package com.cosmos.medical_assistant;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.util.Objects;

//@Component
@AllArgsConstructor
public class TestLlmCall implements CommandLineRunner{

    private final ChatModel chatModel;

    @Override
    public void run(String... args) throws Exception {
        ChatResponse response = chatModel.call(new Prompt("Que es la fiebre?"));
        String content = Objects.requireNonNull(response.getResult()).getOutput().getText();
        System.out.println("====RESPUESTA====");
        System.out.println(content);

        System.out.println("\n====METADATA====");
        System.out.println("modelo: " + response.getMetadata().getModel());

        System.out.println("\n====TOKENS====");
        System.out.println("entrada: " + response.getMetadata().getUsage().getPromptTokens());
        System.out.println("salida: " + response.getMetadata().getUsage().getCompletionTokens());
        System.out.println("total: " + response.getMetadata().getUsage().getTotalTokens());
    }
}
