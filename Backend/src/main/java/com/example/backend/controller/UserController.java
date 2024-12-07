package com.example.backend.controller;

import com.example.backend.dto.LoginRequest;
import com.example.backend.dto.RegisterRequest;
//import com.example.backend.service.UserService;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserService;
import com.example.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@RestController
@CrossOrigin(origins ="http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    private JwtUtil jwtUtil;
    public UserController(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        if (isAuthenticated) {
            String token = jwtUtil.generateToken(loginRequest.getUsername());

            Map<String, String> response = new HashMap<>();
            response.put("token", token);  // Upewnij się, że to jest poprawny format

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "Invalid credentials"));        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest ) {

    if(userRepository.findByUsername(registerRequest.getUsername()) != null) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");

    }
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword()); // Pamiętaj o szyfrowaniu hasła
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());

        userService.registerUser(user);

        return ResponseEntity.ok("User registered successfully");
}

@GetMapping("/verify")
public ResponseEntity<String> verify(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        System.out.println("Header "+authHeader);
    if (authHeader == null) {
        System.out.println("Authorization header is missing");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing Authorization header");
    }
    if (!authHeader.startsWith("Bearer ")) {
        System.out.println("Authorization header is invalid: " + authHeader);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Authorization header format");
    }

    String token = authHeader.replace("Bearer ", "");

    try {
        System.out.println(token);
        String username = jwtUtil.validateToken(token);
        return ResponseEntity.ok(username);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token: " + e.getMessage());
    }
}
}
