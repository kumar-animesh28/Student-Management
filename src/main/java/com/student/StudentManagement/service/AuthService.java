package com.student.StudentManagement.service;

import com.student.StudentManagement.dto.*;

import java.util.Map;

public interface AuthService {
    SignupResponseDto signup(SignupDto signupDto);
    LoginResponseDto login(LoginDto loginDto);
    UserProfileResponseDto getUserProfile(Map<String, Object> claims);
}
