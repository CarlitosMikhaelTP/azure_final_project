package com.example.demo.infrastructure.authentication;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationResponse {

    // Funciona como una DTO

    private String token;
    private Integer idUserType;// Agregando el campo tipo de Usuario
    private Integer idWalker;
    private Integer idOwner;
    private Integer Id;
    private String name;
    private String lastname;
    private Integer idLocationWalker;
    private Integer idLocationOwner;
}
