package com.example.demo.infrastructure.web.projection.classBased;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDTO {

    // Definiendo los campos para el registro de una transaccion
    @NotNull(message = "El id del paseador no puede ser nulo")
    @NotBlank(message = "El id del paseador no puede estar vacío")
    private Integer walkerId;

    // Definiendo los campos para la edición del paseador y su edición
    @NotNull(message = "El id del propietario no puede ser nulo")
    @NotBlank(message = "El id del propietario no puede estar vacío")
    private Integer ownerId;

    @NotNull(message = "El id del tipo de transaccion no puede ser nulo")
    @NotBlank(message = "El id del tipo de transaccion no puede estar vacío")
    private Integer typeTransactionId;

    @NotNull(message = "El id del tipo de transaccion no puede ser nulo")
    @NotBlank(message = "El id del tipo de transaccion no puede estar vacío")
    private Integer stateTransactionId;

    @NotNull(message = "El monto no puede ser nula")
    @NotBlank(message = "El monto no puede estar vacía")
    @Digits(integer = 8, fraction = 2, message = "El monto debe tener un máximo de 10 dígitos enteros y 2 decimales")
    private Double cost;

    // Campos adicionales para enviar respuesta
    private Integer id;
    // id del pasedor y propietario ya estan definidos monto tambien

}
