package com.example.demo.infrastructure.web.projection.classBased;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatDTO {

    private Integer id;

    @NotNull(message = "El id de la sala no puede ser nulo")
    @NotBlank(message = "El id del tipo de mascota no puede estar vacio")
    private Integer roomId;

    @NotNull(message = "El id del propietario no puede ser nulo")
    @NotBlank(message = "El id del propietario no puede estar vacío")
    private Integer userId;

    @NotNull(message = "El id del propietario no puede ser nulo")
    @NotBlank(message = "El id del propietario no puede estar vacío")
    private Integer typeMessageId;

    @NotNull(message = "El nombre de la mascota no puede ser nula")
    @NotBlank(message = "La descripción de la mascota no puede estar vacía")
    @Size(max = 255, message = "La descripción de la mascota debe tener entre 1 y 20 caracteres")
    private String url;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp date;

    @NotNull(message = "El nombre de la mascota no puede ser nula")
    @NotBlank(message = "La descripción de la mascota no puede estar vacía")
    @Size(max = 255, message = "La descripción de la mascota debe tener entre 1 y 20 caracteres")
    private String message;

    // Campos adicionales para enviar respuesta
    private String nameUser;

}
