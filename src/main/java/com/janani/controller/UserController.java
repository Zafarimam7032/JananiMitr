package com.janani.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.janani.entity.User;
import com.janani.model.UserResponse;
import com.janani.model.UserViewModel;
import com.janani.service.UserService;
import com.janani.util.Utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Value("${jwt.key}")
    private String jwtKey;

    @Value("${jwt.subject}")
    private String jwtSubject;

    @Value("${jwt.issuer}")
    private String jwtIssuer;

    @Value("${jwt.audience}")
    private String jwtAudience;

    @GetMapping("/GetUsersById/{id}")
    public ResponseEntity<?> getUsersById(@PathVariable("id") int id) {
        try {
            User userResp = userService.getUserByIdAsync(id).get().get();

            if (userResp.getRchId() <= 0) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(userResp);
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(Utils.getExceptionMessage(ex));
        }
    }

    @GetMapping("/GetUserByMobNumberAsync/{mobNumber}")
    public ResponseEntity<?> getUserByMobNumberAsync(@PathVariable("mobNumber") String mobNumber) {
        try {
            if (mobNumber == null || mobNumber.isEmpty()) {
                return ResponseEntity.badRequest().body("Mobile Number is Required");
            }

            User userResp = userService.getUserByMobNumberAsync(mobNumber).get().get();

            if (userResp == null) {
                return ResponseEntity.status(404).body("User Details Not Found for this Mobile Number");
            }

            String token = generateJwtToken(mobNumber);
            return ResponseEntity.ok(new UserResponse(token, userResp));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(Utils.getExceptionMessage(ex));
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<?> userRegister(@RequestBody UserViewModel viewModel) {
        try {
            if (viewModel == null) {
                return ResponseEntity.badRequest().build();
            }

            User createdUser = userService.registration(viewModel).get().get();
            if (createdUser == null) {
                return ResponseEntity.ok("User Exist Already");
            }

            return ResponseEntity.ok(createdUser);
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error creating new userRegistration record");
        }
    }

    private String generateJwtToken(String username) {
        long expirationTime = 1000 * 60 * 60; // 1 hour
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .setSubject(jwtSubject)
                .setIssuer(jwtIssuer)
                .setAudience(jwtAudience)
                .setClaims(Map.of("UserId", username))
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, jwtKey)
                .compact();
    }

    
}
