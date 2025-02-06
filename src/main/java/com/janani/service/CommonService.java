package com.janani.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.janani.repository.AuthUserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class CommonService implements ICommonService {

	@Autowired
	private AuthUserRepository authUserRepository;
	
    private final Logger logger=LoggerFactory.getLogger(CommonService.class);
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final String API_KEY = "A8sQnY6cXvH2pDg";
    private static final String API_URL = "https://gai.spikeapi.com/v2/nutrition/detailed-analyse";

   

    public boolean isAuthUser(long rchId, String tokenId) {
        boolean isAuthenticated = false;
        if (rchId > 0 && tokenId != null && !tokenId.trim().isEmpty() &&
        		authUserRepository.findAll().stream().anyMatch(user -> user.getUserId() == rchId && user.getTokenId().equals(tokenId))) {
            isAuthenticated = true;
        } else if (rchId > 0 && (tokenId == null || tokenId.trim().isEmpty())) {
            isAuthenticated = true;
        }
        return isAuthenticated;
    }

    public CompletableFuture<String> callNutritionApi(String imageURL) {
        return CompletableFuture.supplyAsync(() -> {
            // Define the request payload
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("userUuid", API_KEY);
            requestBody.put("imageLink", imageURL);

            Map<String, Object> config = new HashMap<>();
            config.put("language", "en");
            config.put("microNutrients", true);
            requestBody.put("config", config);

            // Convert requestBody to JSON
            String jsonRequestBody = new Gson().toJson(requestBody);
            String responseBody = "";

            try {
                // Prepare the HTTP request
                ResponseEntity<String> response = restTemplate.exchange(
                        API_URL,
                        HttpMethod.POST,
                        new HttpEntity<>(jsonRequestBody, createHeaders(API_KEY)),
                        String.class
                );

                // Ensure the response is successful
                if (response.getStatusCode() == HttpStatus.OK) {
                    responseBody = response.getBody();
                }
            } catch (Exception e) {
                logger.error("Request error: {}" , e.getMessage());
                responseBody = "";
            }

            return responseBody;
        });
    }

    private HttpHeaders createHeaders(String apiKey) {
        return new HttpHeaders() {{
            set("X-API-Key", apiKey);
            setContentType(MediaType.APPLICATION_JSON);
        }};
    }
}
