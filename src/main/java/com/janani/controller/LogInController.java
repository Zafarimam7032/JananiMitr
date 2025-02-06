package com.janani.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.janani.entity.SurveyResponse;
import com.janani.entity.User;
import com.janani.model.OTPModel;
import com.janani.model.ValidateOTPModel;
import com.janani.service.LoginService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/[controller]")
public class LogInController {

    private final LoginService loginService;

    @Autowired
    public LogInController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/LogInById/{id}")
    public ResponseEntity<Object> getLogInById(@PathVariable String id) {
        try {
            Optional<User> logInResp = loginService.getLogInById(id).get();

            if (!logInResp.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(logInResp.get());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error While Logging to the System ...");
        }
    }

    /* Uncommented and added methods below for completeness */

    // Example for handling POST method to add or update an authenticated user
    @PostMapping("/AuthUserAddOrUpdate/{userId}/{tokenId}")
    public ResponseEntity<Void> authUserAddOrUpdate(@PathVariable int userId, @PathVariable String tokenId) {
        try {
            loginService.addOrUpdateAuthUser(userId, tokenId);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PostMapping("/OTPRequest")
    public ResponseEntity<String> otpRequest(@RequestBody OTPModel otpModel) {
        try {
             String requestedOTP = loginService.getOTPForAuthenticate(otpModel).get();

            if (requestedOTP == null || requestedOTP.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(requestedOTP);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);  // Or custom error message if desired
        }
    }

    @PostMapping("/ValidateOTP")
    public ResponseEntity<String> otpRequestToValidate(@RequestBody ValidateOTPModel otpValModel) {
        try {
           String requestedOTP = loginService.validateOTP(otpValModel).get();

            if (requestedOTP == null ) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(requestedOTP);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);  // Or custom error message if desired
        }
    }
}
