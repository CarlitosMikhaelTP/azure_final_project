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
public class RatingWalkerDTO {

    private Integer id;

    // Definiendo campos para la registrar y actualizar la locación del paseador
    @NotNull(message = "El id de Paseador no puede ser nula")
    @NotBlank(message = "El id del Paseador no puede estar vacía")
    private Integer idWalker;

    private Integer idWalk;

    private Integer rating;

}
