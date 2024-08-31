package com.example.demo.application.service.UserService;

import com.example.demo.infrastructure.web.projection.classBased.UserDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.UserProjection;

import java.util.List;
import java.util.Optional;

public interface UserService {

    // Implementacion para mostrar user registrados con proyecciones
    List<UserProjection> findAllUsersService();

    // Implementación para mostrar un usuario registrado por su ID con proyeccion
    Optional<UserProjection> findUserById(Integer id);

    // Implementación para editar un usuario registrado por medio de su ID
    UserDTO ediUserDetailsService(Integer id, UserDTO request);

    // Implementación para eliminar un usuario por su ID
    boolean deleteUserByIdService(Integer id);


}
