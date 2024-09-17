package com.example.demo.infrastructure.web.projection.classBased;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentWalkerDTO {

    // Id del comentario
    private Integer id;

    @NotNull(message = "El id del paseo no puede ser nulo")
    @NotBlank(message = "El id del paseo no puede estar vacio")
    private Integer walkerId;

    @NotNull(message = "El id del paseo no puede ser nulo")
    @NotBlank(message = "El id del paseo no puede estar vacio")
    private Integer walkId;

    @NotNull(message = "El id del paseo no puede ser nulo")
    @NotBlank(message = "El id del paseo no puede estar vacio")
    private Integer userId;

    @NotNull(message = "El comentario no puede ser nula")
    @NotBlank(message = "La comentario no puede estar vacía")
    @Size(max = 200, message = "El comentario debe tener entre 1 y 255 caracteres")
    private String comment;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp date;

}
