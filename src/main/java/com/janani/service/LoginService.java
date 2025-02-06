package com.janani.service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.janani.entity.AuthUser;
import com.janani.entity.User;
import com.janani.model.OTPModel;
import com.janani.model.ValidateOTPModel;
import com.janani.repository.AuthUserRepository;
import com.janani.repository.UserRepository;

@Service
public class LoginService implements ILoginService {

    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public CompletableFuture<Optional<User>> getLogInById(String id) {
        return CompletableFuture.supplyAsync(() -> userRepository.findAll()
                .stream()
                .filter(user -> user.getMobileNumber().equals(id))
                .findFirst());
    }

    @Override
    public CompletableFuture<Void> addOrUpdateAuthUser(int userId, String tokenId) {
        return CompletableFuture.runAsync(() -> {
            AuthUser authUser = new AuthUser();
            authUser.setUserId(userId);
            authUser.setTokenId(tokenId);
            authUser.setLastLoginAt(new Date());

            boolean exists = authUserRepository.findAll().stream()
                    .anyMatch(user -> user.getUserId() == authUser.getUserId());

            if (exists) {
            	authUserRepository.findAll().removeIf(user -> user.getUserId() == authUser.getUserId());
            	authUserRepository.findAll().add(authUser);
            } else {
            	authUserRepository.findAll().add(authUser);
            }
            authUserRepository.save(authUser);
        });
    }

    @Override
    public CompletableFuture<String> getOTPForAuthenticate(OTPModel otpModel) {
        return CompletableFuture.supplyAsync(() -> {
            String url = "https://uat-api-oneaig.aighospitals.com:8090/api/auth/otp/generate";
            Map<String, Object> payload = new HashMap<>();
            payload.put("phoneNo", otpModel.getPhoneNo());
            payload.put("requestedFrom", otpModel.getRequestedFrom());
            payload.put("type", otpModel.getType());

            try {
                String jsonPayload = objectMapper.writeValueAsString(payload);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);

                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
                return response.getBody();
            } catch (Exception e) {
                throw new RuntimeException("Error generating OTP", e);
            }
        });
    }

    @Override
    public CompletableFuture<String> validateOTP(ValidateOTPModel valOtpModel) {
        return CompletableFuture.supplyAsync(() -> {
            String url = "https://uat-api-oneaig.aighospitals.com:8090/api/auth/otp/validate";
            Map<String, Object> payload = new HashMap<>();
            payload.put("phoneNo", valOtpModel.getPhoneNo());
            payload.put("requestId", valOtpModel.getRequestId());
            payload.put("otp", valOtpModel.getOtp());

            try {
                String jsonPayload = objectMapper.writeValueAsString(payload);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);

                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
                return response.getBody();
            } catch (Exception e) {
                throw new RuntimeException("Error validating OTP", e);
            }
        });
    }
}
