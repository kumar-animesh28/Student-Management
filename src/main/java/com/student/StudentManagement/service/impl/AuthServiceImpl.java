package com.student.StudentManagement.service.impl;

import com.student.StudentManagement.dto.LoginDto;
import com.student.StudentManagement.dto.LoginResponseDto;
import com.student.StudentManagement.dto.SignupDto;
import com.student.StudentManagement.dto.SignupResponseDto;
import com.student.StudentManagement.entity.Student;
import com.student.StudentManagement.repository.StudentRepository;
import com.student.StudentManagement.security.JwtUtil;
import com.student.StudentManagement.service.AuthService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public SignupResponseDto signup(SignupDto signupDto) {
        Student newStudent = modelMapper.map(signupDto, Student.class);
        newStudent.setPassword(bCryptPasswordEncoder.encode(signupDto.getPassword()));
        Student student = studentRepository.save(newStudent);

        SignupResponseDto signupResponseDto = modelMapper.map(student, SignupResponseDto.class);
        signupResponseDto.setMessage("Signup Successful");

        return signupResponseDto;
    }

    @Override
    public LoginResponseDto login(LoginDto loginDto) {
        Student student = studentRepository.findByEmail(loginDto.getUsername())
                .or(() -> studentRepository.findByMobile(loginDto.getUsername()))
                .orElseThrow(() -> new RuntimeException("User not Found"));

        if(!bCryptPasswordEncoder.matches(loginDto.getPassword(), student.getPassword())){
            throw new RuntimeException("Invalid Credentials");
        }

        Map<String, Object> claims = Map.of(
                "id", student.getId(),
                "email", student.getEmail(),
                "mobile", student.getMobile()
        );

        String token = jwtUtil.generateToken(claims);

        return new LoginResponseDto("Login Successful", token);
    }
}
