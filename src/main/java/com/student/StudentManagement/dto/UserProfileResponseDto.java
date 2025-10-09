package com.student.StudentManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponseDto {
    private String message;
    private String id;
    private String name;
    private String mobile;
    private String email;
}
