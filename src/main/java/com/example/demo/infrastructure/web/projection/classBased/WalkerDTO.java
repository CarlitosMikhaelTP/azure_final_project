package com.example.demo.infrastructure.web.projection.classBased;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalkerDTO {

    private Integer id;

    // Definiendo los campos para el registro del paseador
    @NotNull(message = "El id de usuario no puede ser nula")
    @NotBlank(message = "El id del usuario no puede estar vacía")
    private Integer idUser;

    // Definiendo los campos para la edición del paseador y su edición
    //@NotNull(message = "El id de la categoría no puede ser nula")
    //@NotBlank(message = "El id de la categoría no puede estar vacía")
    private Integer idCategory;

    private String foto;

    //@NotNull(message = "La calificacion no puede ser nula")
    //@NotBlank(message = "La calificacion no puede estar vacía")
    private Integer calification;

    //@NotNull(message = "La descripción no puede ser nula")
    //@NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 255, message = "La descripción debe tener entre 1 y 255 caracteres")
    private String description;

    //@NotNull(message = "La experiencia no puede ser nula")
    //@NotBlank(message = "La experiencia no puede estar vacía")
    @Size(max = 255, message = "La experiencia debe tener entre 1 y 255 caracteres")
    private String experience;

    private String prefer_race;
    private Integer number_walk;

    //@NotNull(message = "La ubicación no puede ser nula")
    //@NotBlank(message = "La ubicació no puede estar vacía")
    @Size(max = 20, message = "La ubicación debe tener entre 1 y 20 caracteres")
    private String location;

    //@NotNull(message = "La tarifa no puede ser nula")
    //@NotBlank(message = "La tarifa no puede estar vacía")
    @Digits(integer = 10, fraction = 2, message = "La tarifa debe tener un máximo de 10 dígitos enteros y 2 decimales")
    private Double cost;

    //@NotNull(message = "El saldo no puede ser nula")
    //@NotBlank(message = "La tarifa no puede estar vacía")
    @Digits(integer = 10, fraction = 2, message = "El saldo debe tener un máximo de 10 dígitos enteros y 2 decimales")
    private Double money;

    @NotNull(message = "La disponibilidad no puede ser nula")
    @NotBlank(message = "La disponibilidad no puede estar vacía")
    private Boolean availability;

    // Campos adicionales para enviar respuesta
    private Integer idTypeUser;

    private String name;


}
