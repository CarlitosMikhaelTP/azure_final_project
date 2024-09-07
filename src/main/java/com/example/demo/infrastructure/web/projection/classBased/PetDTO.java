package com.example.demo.infrastructure.web.projection.classBased;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetDTO {

    // Definiendo los campos para el registro del paseador
    @NotNull(message = "El id del tipo de mascota no puede ser nulo")
    @NotBlank(message = "El id del tipo de mascota no puede estar vacio")
    private Integer typePetId;

    // Definiendo los campos para la edición del paseador y su edición
    @NotNull(message = "El id del propietario no puede ser nulo")
    @NotBlank(message = "El id del propietario no puede estar vacío")
    private Integer ownerId;

    private Integer badHabits;

    private String nameBadHabit;

    private String foto;

    @NotNull(message = "El nombre de la mascota no puede ser nula")
    @NotBlank(message = "La descripción de la mascota no puede estar vacía")
    @Size(max = 20, message = "La descripción de la mascota debe tener entre 1 y 20 caracteres")
    private String name;

    @NotNull(message = "La raza no puede ser nula")
    @NotBlank(message = "La raza no puede estar vacía")
    @Size(max = 20, message = "La raza debe tener entre 1 y 20 caracteres")
    private String race;

    @NotNull(message = "El peso no puede ser nulo")
    @NotBlank(message = "El peso no puede estar vacío")
    @Size(max = 20, message = "El peso debe tener entre 1 y 20 caracteres")
    private String weight;

    @NotNull(message = "La edad de la mascota no puede ser nulo")
    @NotBlank(message = "La edad de la mascota no puede estar vacío")
    @Size(max = 20, message = "El edad de la mascota debe tener entre 1 y 20 caracteres")
    private String age;

    @NotNull(message = "La necesidad de la mascota no puede ser nulo")
    @NotBlank(message = "La necesidad de la mascota no puede estar vacío")
    @Size(max = 255, message = "El edad de la mascota debe tener entre 1 y 255 caracteres")
    private String needs;


    // Campos adicionales para enviar respuesta
    private Integer id;
    private Integer typerUserId;
    private Integer userId;
    private String nameOwner;


}
