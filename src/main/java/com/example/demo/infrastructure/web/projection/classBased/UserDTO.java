package com.example.demo.infrastructure.web.projection.classBased;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Integer id;

    @NotNull(message = "El nombre del usuario no puede ser nulo")
    @NotBlank(message = "El nombre del usuario no puede estar vacía")
    @Size(max = 20)// Estableciendo el mínimo y máximom de caracteres en Strings
    private String name;

    @NotNull(message = "El apellido del usuario no puede ser nulo")
    @NotBlank(message = "El apellido del usuario no puede estar vacío")
    @Size(max = 20)
    private String lastName;

    @NotNull(message = "El apodo del usuario no puede ser nulo")
    @NotBlank(message = "El apodo del usuario no puede estar vacío")
    @Size(max = 20)
    private String nickame;

    @NotNull(message = "La direccion del usuario no puede ser nulo")
    @NotBlank(message = "El direccion del usuario no puede estar vacía")
    @Size(max = 20)
    private String address;

    @NotNull(message = "La edad del usuario no puede ser nula")
    @NotBlank(message = "La edad del usuario no puede estar vacía")
    @Min(value = 18, message = "La edad mínima es 18")
    @Max(value = 80, message = "La edad máxima es 100")
    private Integer age;

    @NotNull(message = "El celular del usuario no puede ser nulo")
    @NotBlank(message = "El celular del usuario no puede estar vacío")
    @Size(max = 9)
    private String celphone;

    @NotNull(message = "El dni del usuario no puede ser nulo")
    @NotBlank(message = "El dni del usuario no puede estar vacía")
    @Size(max = 8)
    private String dni;

    @NotNull(message = "El email del usuario no puede ser nulo")
    @NotBlank(message = "El email del usuario no puede estar vacía")
    @Size(max = 50)
    @Email
    private String email;

    @NotNull(message = "La contraseña del usuario no puede ser nulo")
    @NotBlank(message = "La contraseña del usuario no puede estar vacía")
    @Size(max = 255)
    private String password;
}
