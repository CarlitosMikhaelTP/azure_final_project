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
public class RoomDTO {

    private Integer id;

    @NotNull(message = "El id de la sala no puede ser nulo")
    @NotBlank(message = "El id del tipo de mascota no puede estar vacio")
    private Integer typeRoomId;

    @NotNull(message = "El id del propietario no puede ser nulo")
    @NotBlank(message = "El id del propietario no puede estar vacío")
    private Integer bookingId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp date;


}
