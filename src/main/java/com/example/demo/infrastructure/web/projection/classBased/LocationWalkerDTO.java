package com.example.demo.infrastructure.web.projection.classBased;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationWalkerDTO {

    private Integer id;

    // Definiendo campos para la registrar y actualizar la locación del paseador
    @NotNull(message = "El id de Paseador no puede ser nula")
    @NotBlank(message = "El id del Paseador no puede estar vacía")
    private Integer idWalker;

    @NotNull(message = "La latitud no puede ser nula")
    @NotBlank(message = "La latitud no puede estar vacía")
    @Digits(integer = 38, fraction = 7, message = "La tarifa debe tener un máximo de 38 dígitos enteros y 7 decimales")
    private BigDecimal latitude;

    @NotNull(message = "La longitud no puede ser nula")
    @NotBlank(message = "La longitud no puede estar vacía")
    @Digits(integer = 38, fraction = 7, message = "La tarifa debe tener un máximo de 38 dígitos enteros y 7 decimales")
    private BigDecimal lenght;


    // Campos adicionales para mostrar como respuesta
    private Integer idUser;
    private Integer idTypeUser;
    private String name;
    private String lastname;
}
