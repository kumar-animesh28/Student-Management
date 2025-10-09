package com.student.StudentManagement.controller;

import com.student.StudentManagement.dto.*;
import com.student.StudentManagement.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@Valid @RequestBody SignupDto signupDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(signupDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto loginDto) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginDto));
    }

    @GetMapping("/userProfile")
    public ResponseEntity<UserProfileResponseDto> getUserProfile(Authentication authentication) {
        Map<String, Object> claims = (Map<String, Object>) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body(authService.getUserProfile(claims));
    }
}
