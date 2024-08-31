package com.example.demo.infrastructure.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    // Representación del DTO
    private String name;
    private String lastName;
    private Integer idUserType;
    private String nickname;
    private String address;
    private Integer age;
    private String celphone;
    private String dni;
    private String email;
    private String password;
}
