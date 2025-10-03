package com.student.StudentManagement.service;

import com.student.StudentManagement.dto.LoginDto;
import com.student.StudentManagement.dto.LoginResponseDto;
import com.student.StudentManagement.dto.SignupDto;
import com.student.StudentManagement.dto.SignupResponseDto;

import java.util.Map;

public interface AuthService {
    SignupResponseDto signup(SignupDto signupDto);
    LoginResponseDto login(LoginDto loginDto);
}
