package com.example.demo.infrastructure.web.projection.classBased;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalkDTO {

    // Campos para registrar y editar
    @NotNull(message = "El id de reserva no puede ser nula")
    @NotBlank(message = "El id del reserva no puede estar vacía")
    private Integer bookingId;

    @Future(message = "La fecha de paseo debe estar en el futuro")
    @NotNull(message = "La fecha de paseo no puede ser nula")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp start;

    @NotNull(message = "La duración del paseo no puede ser nula")
    @Min(value = 30, message = "La duración mínima del paseo es de 30 minutos")
    @Max(value = 240, message = "La duración máxima del paseo es de 240 minutos (4 horas)")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime duration;

    @NotNull(message = "El detalle no puede ser nulo")
    @NotBlank(message = "El detalle no puede estar vacío")
    @Size(min = 1, max = 25, message = "El monto debe tener un máximo de 255 caracteres")
    private String placeWalk;

    @NotNull(message = "El comentario no puede ser nulo")
    @NotBlank(message = "El comentario no puede estar vacío")
    @Size(min = 1, max = 255, message = "El monto debe tener un máximo de 255 caracteres")
    private String comment;

    //@NotNull(message = "La calificacion del paseo puede ser nula")
    //@NotBlank(message = "El calificacion del paseo no puede estar vacía")
    //private Integer calification;

    @NotNull(message = "El costo no puede ser nulo")
    @NotBlank(message = "El costo no puede estar vacío")
    @Digits(integer = 10, fraction = 2, message = "El costo debe tener un máximo de 10 dígitos enteros y 2 decimales")
    private Double cost;

    // Respuesta para el FRONTEND , ya esta el id de la reserva y fecha de paseo y costo
    private Integer id;
    private Integer ownerId;
    private Integer walkerId;

    private String nameWalker;
    private String nameOwner;

}
