package com.example.demo.infrastructure.web.projection.classBased;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayRequestDTO {

    // Definiendo los campos para el registro de una transaccion
    @NotNull(message = "El id del paseador no puede ser nulo")
    @NotBlank(message = "El id del paseador no puede estar vacío")
    private Integer IdWalker;

    // Definiendo los campos para la edición del paseador y su edición
    @NotNull(message = "El id del propietario no puede ser nulo")
    @NotBlank(message = "El id del propietario no puede estar vacío")
    private Integer IdOwner;

    @NotBlank(message = "El id del paseo de transaccion no puede estar vacío")
    @NotNull(message = "El ID del paseo no puede estar vacío")
    private Integer idPaseo;

    @NotNull(message = "El id del tipo de transaccion no puede ser nulo")
    @NotBlank(message = "El id del tipo de transaccion no puede estar vacío")
    private Integer IdEstadoTransaccion;

    @NotNull(message = "El id del tipo de transaccion no puede ser nulo")
    @NotBlank(message = "El id del tipo de transaccion no puede estar vacío")
    private Integer IdTipoTransaccion;

}
