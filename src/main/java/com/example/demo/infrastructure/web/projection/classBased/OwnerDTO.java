package com.example.demo.infrastructure.web.projection.classBased;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDTO {

    private Integer id;

    // Creando DTO para realizar el registro y edición de Propietario
    @NotNull(message = "El id de usuario no puede ser nula")
    @NotBlank(message = "El id del usuario no puede estar vacía")
    private Integer idUser;

    //@NotNull(message = "La calificacion no puede ser nula")
    //@NotBlank(message = "la calificacion no puede estar vacía")
    private Integer calification;

    //@NotNull(message = "El comentario no puede ser nula")
    //@NotBlank(message = "El comentario no puede estar vacía")
    @Size(max = 255, message = "El comentario no debe tener entre 1 y 255 caracteres")
    private String comment;

    //@NotNull(message = "La preferencia del paseo no puede ser nula")
    //@NotBlank(message = "La preferencia del paseo no puede estar vacía")
    @Size(max = 255, message = "La preferencia del paseo no debe tener entre 1 y 255 caracteres")
    private String walkingPreferences;

    //@NotNull(message = "El saldo no puede ser nula")
    //@NotBlank(message = "El saldo no puede estar vacía")
    @Digits(integer = 10, fraction = 2, message = "La tarifa debe tener un máximo de 10 dígitos enteros y 2 decimales")
    private Double money;

    private String foto;

    //@NotNull(message = "La disponibilidad no puede ser nula")
    //@NotBlank(message = "La disponibilidad no puede estar vacía")
    private Boolean availability;

    //@NotNull(message = "La ubicación no puede ser nula")
    //@NotBlank(message = "La ubicació no puede estar vacía")
    @Size(max = 20, message = "La ubicación debe tener entre 1 y 20 caracteres")
    private String location;

    // Campos adicionales como respuesta
    private Integer idTypeUser;
    private Integer idOwner;
    private String name;

}
