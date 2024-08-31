package com.example.demo.infrastructure.web.projection.interfaceBased;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Value;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface UserProjection {

    // La convención de nomenclatura de Spring Data JPA sigue un patrón
    // donde interpreta los métodos en la interfaz de proyección según
    // los nombres de las propiedades de la entidad. En este caso, al
    // nombrar el método como getUserNombres(), Spring Data JPA asumirá
    // que está accediendo al campo nombres de la entidad User. Esto es
    // posible gracias al análisis de la expresión del método y su mapeo
    // con los campos de la entidad.

    Integer getId();
    String getName();
    String getLastName();
    String getNickname();
    String getAddress();
    Integer getAge();
    String getCelphone(); // Nota: esto coincide con el nombre del campo en tu entidad User
    String getDni();
    String getEmail();

    // Puedes añadir un método para obtener el tipo de usuario si lo necesitas
    @Value("#{target.typeUserId.nameTypeUser}")
    String getTypeUserName();

    // Si quieres incluir el estado del usuario
    Short getState();

    // Si quieres incluir las fechas de creación y actualización
    // java.sql.Timestamp getCreatedAt();
    // java.sql.Timestamp getUpdatedAt();

    // No incluimos password por razones de seguridad

}
