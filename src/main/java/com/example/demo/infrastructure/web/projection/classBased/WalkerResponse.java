package com.example.demo.infrastructure.web.projection.classBased;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// DEFINIENDO DTO QUE SALDRA COMO RESPUESTA AL REGISTRAR Y EDITAR UN USUARIO
public class WalkerResponse {

    private Integer id;
    private Integer IdUser;
    private String nameCategory;
}
