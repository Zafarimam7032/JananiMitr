package com.janani.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@Service
public class GeminiService implements IGeminiService {

    private final String apiKey;
    private final String baseUrl;
    private final Logger logger;
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public GeminiService(@Value("${GeminiApi.ApiKey}") String apiKey,
                         @Value("${GeminiApi.BaseUrl}") String baseUrl,
                         Logger logger) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.logger = logger;
    }

    @Override
    public CompletableFuture<String> getAnswerAsync(String query, String language) {
        if (query == null || query.trim().isEmpty()) {
            throw new IllegalArgumentException("Query cannot be null or empty.");
        }
        if (language == null || language.trim().isEmpty()) {
            throw new IllegalArgumentException("Language cannot be null or empty.");
        }

        return CompletableFuture.supplyAsync(() -> {
            String responseBody = "";

            // Build the request body with correct history for pregnancy-related question constraint
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("contents", new Map[]{
                Map.of("parts", new Map[]{Map.of("text", "Hello, please only answer questions related to pregnancy. If not, kindly ask a pregnancy-related question")},
                       "role", "user"), // "user" role for initial instruction
                Map.of("parts", new Map[]{Map.of("text", "Great to meet you. What would you like to know?")},
                       "role", "model"), // "model" role for initial response
                Map.of("parts", new Map[]{Map.of("text", query + " answer this in " + language)},
                       "role", "user") // "user" role for the question
            });

            Map<String, Object> generationConfig = new HashMap<>();
            generationConfig.put("temperature", 1);
            generationConfig.put("topP", 0.95);
            generationConfig.put("topK", 40);
            generationConfig.put("maxOutputTokens", 8192);
            generationConfig.put("responseMimeType", "text/plain");
            requestBody.put("generationConfig", generationConfig);

            try {
                // Prepare the HTTP request
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.set("X-API-Key", apiKey);

                HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), headers);

                ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + "/v1beta/models/gemini-1.5-flash-latest:generateContent", entity, String.class);

                // Ensure the response is successful
                if (response.getStatusCode().is2xxSuccessful()) {
                    responseBody = response.getBody();
                }

                // Log the entire response content for debugging
                logger.info("Response Content: " + responseBody);

                // Deserialize the response content
                JsonNode responseJson = objectMapper.readTree(responseBody);

                // Check if the 'candidates' key exists and retrieve the first candidate
                if (responseJson.has("candidates") && responseJson.get("candidates").size() > 0) {
                    JsonNode candidate = responseJson.get("candidates").get(0);

                    // Get the text content from the 'parts' array
                    String textContent = candidate.get("content").get("parts").get(0).get("text").asText();
                    return textContent != null ? textContent : "No content returned.";
                } else {
                    throw new IllegalArgumentException("No candidates found in the response.");
                }
            } catch (Exception e) {
                logger.severe("Error calling Gemini API: " + e.getMessage());
                throw new IllegalStateException("Error calling Gemini API: " + e.getMessage(), e);
            }
        });
    }
}
