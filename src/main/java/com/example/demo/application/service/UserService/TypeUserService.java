package com.example.demo.application.service.UserService;

import com.example.demo.domain.entity.user.TypeUser;
import com.example.demo.domain.repository.User.TypeUserRepository;

import java.util.List;

// En esta clase, no necesita hacer uso de interfaces porque la información que agregaré o insertaré lo haré por la base
// de datos debido a que no necesito implementar lógica o CRUD para esta clase, sino solo usar su contenido como un
// atributo de la clase User.
public class TypeUserService {

    // Lo declaramos como constante la variable tipoUsuarioRepository porque cuando se creen instancias del mismo
    // no queremos que se alteren
    private final TypeUserRepository typeUserRepository;

    public TypeUserService(TypeUserRepository typeUserRepository){
        this.typeUserRepository = typeUserRepository;
    }

    public List<TypeUser> obtenerTodosLosTiposUsuario(){
        return typeUserRepository.findAll();
    }
}
