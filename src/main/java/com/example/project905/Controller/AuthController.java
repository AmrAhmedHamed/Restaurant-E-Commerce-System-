package com.example.project905.Controller;

import com.example.project905.Controller.Auth.LoginRequest;
import com.example.project905.Dto.UserDto;
import com.example.project905.Jwt.JwtUtil;
import com.example.project905.Modle.User;
import com.example.project905.Service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:4200")
@Tag(name = "AuthController ", description = "APIs for signup and login")

public class AuthController {

    @Autowired
    private AuthService service;

    @Autowired
    private JwtUtil jwt;
    @Operation(summary = "User registration", description = "Register a new user account")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully registered user"),
            @ApiResponse(responseCode = "400", description = "Invalid user data or username already exists")
    })
    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @Valid @RequestBody UserDto dto,
            Locale locale
    ) {
        return ResponseEntity.ok(
                Collections.singletonMap("message", service.signup(dto, locale))
        );
    }
    @Operation(summary = "User login", description = "Authenticate user and return JWT token")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully logged in"),
            @ApiResponse(responseCode = "400", description = "Invalid credentials")
    })

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req, Locale locale) {
        try {
            User user = service.login(req, locale);
            String token = jwt.generateToken(user);

            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "username", user.getUsername(),
                    "role", user.getRole()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", "Login failed: " + e.getMessage())
            );
        }
    }
}