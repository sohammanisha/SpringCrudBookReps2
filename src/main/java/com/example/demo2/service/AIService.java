package com.example.demo2.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import org.springframework.core.env.PropertySource;
import com.example.demo2.exception.AIServiceException; 

@Service

public class AIService {

    private final WebClient webClient;

    @Value("${ai.api.key}")
    private String apiKey;
    
    @Value("${spring.ai.openai.api-key}")
    private String apiKey2;

    @Value("${ai.api.url}")
    private String apiUrl;
    /*
    public AIService() {
        this.webClient = WebClient.builder().build();
    }
    */
    public AIService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }
    
    /*
    public Mono<String> generateBookInsight(String prompt) {
        return webClient.post()
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(Map.of(
                        "model", "gpt-4", // Change model if needed
                        "messages", new Object[]{ Map.of("role", "user", "content", prompt) }
                ))
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> (String) ((Map<?, ?>) ((Object[]) response.get("choices"))[0]).get("text"))
                .defaultIfEmpty("AI could not generate insights.");
    }
    
    
    public Mono<String> generateBookInsight(String prompt) {
    	System.out.println("AI API URL: " + apiUrl + " with api key:" + apiKey + " with apiKey2: "+ apiKey2);
    	if (apiUrl == null || apiUrl.isEmpty()) {
            return Mono.error(new IllegalArgumentException("AI API URL is not configured properly."));
        }
        return webClient.post()
            .header("Authorization", "Bearer " + apiKey)
            .header("Content-Type", "application/json")
            .bodyValue(Map.of(
                "model", "gpt-4",
                "messages", new Object[]{ Map.of("role", "user", "content", prompt) }
            ))
            .retrieve()
            .onStatus(HttpStatusCode::isError, response ->  
                response.bodyToMono(String.class)  // Extract error message
                        .flatMap(errorBody -> Mono.error(new AIServiceException("AI Service Failed: " + errorBody)))  // ✅ Convert to Throwable
            )
            .bodyToMono(Map.class)
            .map(response -> (String) ((Map<?, ?>) ((Object[]) response.get("choices"))[0]).get("text"))
            .defaultIfEmpty("AI could not generate insights.");
    }
    */
    public Mono<String> generateBookInsight(String prompt) {
        if (apiUrl == null || apiUrl.isEmpty()) {
            return Mono.error(new IllegalArgumentException("AI API URL is missing in application.properties!"));
        }

        return webClient.post()
                .uri(apiUrl)
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(Map.of(
                        "model", "text-embedding-ada-002",
                        "messages", new Object[]{ Map.of("role", "user", "content", prompt) }
                ))
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> (String) ((Map<?, ?>) ((Object[]) response.get("choices"))[0]).get("text"))
                .defaultIfEmpty("AI could not generate insights.");
    }
}