package com.example.demo.domain.serviceImpl.User;

import com.example.demo.application.service.UserService.UserService;
import com.example.demo.domain.entity.user.User;
import com.example.demo.domain.repository.User.UserRepository;
import com.example.demo.infrastructure.security.ProjectService;
import com.example.demo.infrastructure.web.projection.classBased.UserDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.UserProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProjectService projectService;

    // Servicio para Listar a los user
    @Override
    public List<UserProjection> findAllUsersService() {
        return userRepository.findAllProjectedBy();
    }

    // Servicio para listar un registro por ID
    @Override
    public Optional<UserProjection> findUserById(Integer id) {
        return userRepository.findProjectedById(id);
    }

    // Servicio para editar un usuario por su ID
    @Override
    public UserDTO ediUserDetailsService(Integer id, UserDTO request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (request.getName() != null) {
            user.setName(request.getName());
        }
        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }
        if (request.getNickame() != null) {
            user.setNickname(request.getNickame());
        }
        if (request.getAddress() != null) {
            user.setAddress(request.getAddress());
        }
        if (request.getAge() != null) {
            user.setAge(request.getAge());
        }
        if (request.getCelphone() != null) {
            user.setCelphone(request.getCelphone());
        }
        if (request.getDni() != null) {
            user.setDni(request.getDni());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }

        // Personalizando Respuesta
        //Integer idUser = request.getId();
        //String name =  request.getName();
        //String lastName =  request.getLastName();
        //String nickName = request.getNickame();
       // String address = request.getAddress();
       // Integer age = request.getAge();
        //String cellphone = request.getCelphone();
        //String dni =  request.getDni();
        //String emial = request.getEmail();

        // Si la contraseña se actualiza, codifícala y genera un nuevo token
        if (request.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            // Generando un nuevo token con la informacion actualizada del usuario
            var jwtToken = projectService.generateToken(user);
            // Guardando cambios en la base de datos
            userRepository.save(user);
            // Devolver nuevo token
            return UserDTO.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .lastName(user.getLastName())
                    .nickame(user.getNickname())
                    .address(user.getAddress())
                    .age(user.getAge())
                    .celphone(user.getCelphone())
                    .dni(user.getDni())
                    .email(user.getEmail())
                    .build();
                     //"Usuario actualizado con éxito\nId del usuario actualizado: "
                       //      + user.getId() + "\nNuevo token: " + jwtToken;

        }
        userRepository.save(user);
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .nickame(user.getNickname())
                .address(user.getAddress())
                .age(user.getAge())
                .celphone(user.getCelphone())
                .dni(user.getDni())
                .email(user.getEmail())
                .build();
        //"Usuario actualizado con éxito\nId del usuario actualizado: "
        //      + user.getId() + "\nNuevo token: " + jwtToken;
    }

    // Servicio para eliminar un usuario por su ID
    @Override
    public boolean deleteUserByIdService(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        userRepository.delete(user);
        System.out.println("Se eliminó al usuario con ID: "+ id);
        return true;
    }

}


