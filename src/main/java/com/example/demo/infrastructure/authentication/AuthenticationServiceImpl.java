package com.example.demo.infrastructure.authentication;

import com.example.demo.application.exceptions.UserExceptions.NotFound.TypeUserNotFoundException;
import com.example.demo.domain.entity.walker.LocationWalker;
import com.example.demo.domain.entity.walker.Walker;
import com.example.demo.domain.entity.owner.LocationOwner;
import com.example.demo.domain.entity.owner.Owner;
import com.example.demo.domain.entity.user.TypeUser;
import com.example.demo.domain.entity.user.User;
import com.example.demo.domain.repository.Walker.LocationWalkerRepository;
import com.example.demo.domain.repository.Owner.LocationOwnerRepository;
import com.example.demo.domain.repository.User.TypeUserRepository;
import com.example.demo.domain.repository.User.UserRepository;
import com.example.demo.infrastructure.security.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository repository;
    private final LocationOwnerRepository locationOwnerRepository;
    private final LocationWalkerRepository locationWalkerRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProjectService projectService;
    private final AuthenticationManager authenticationManager;
    private final TypeUserRepository typeUserRepository;

    @Override
    public AuthenticationResponse register(RegisterRequest request, Integer idUserType) {
        TypeUser typeUser = typeUserRepository.findById(idUserType)
                .orElseThrow(() -> new TypeUserNotFoundException("User type not found"));
        var user = User.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .nickname(request.getNickname())
                .address(request.getAddress())
                .age(request.getAge())
                .celphone(request.getCelphone())
                .dni(request.getDni())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .typeUserId(typeUser)
                .build();

        repository.save(user);
        var jwtToken = projectService.generateToken(user);


        Integer userTypeId = typeUser.getId();
        Integer idUser = user.getId();
        Integer idWalker = null;
        Walker walker = user.getWalker();
        if (walker != null) {
            idWalker = walker.getId();
        }
        String name = user.getName();
        String lastname = user.getLastName();
        Integer idOwner = null;
        Owner owner = user.getOwner();
        if (owner != null) {
            idOwner = owner.getId();
        }


        return AuthenticationResponse.builder()
                .token(jwtToken)
                .idUserType(userTypeId)
                .idWalker(idWalker)
                .idOwner(idOwner)
                .Id(idUser)
                .name(name)
                .lastname(lastname)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = repository.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        user = repository.findUserWithTypeById(user.getId());
        var jwtToken = projectService.generateToken(user);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        TypeUser typeUser = user.getTypeUserId();
        Integer typeUserId = typeUser.getId();
        Integer idUser = user.getId();
        Integer idLocationWalker = null;
        Integer idlocationOwner = null;
        Integer idOwner = null;
        // Verificar si el usuario tiene perfil de paseador
        Integer idWalker = null;
        Walker walker = user.getWalker();
        if (walker != null) {
            idWalker = walker.getId();
            Optional<LocationWalker> locacionPaseadorOptional = locationWalkerRepository.findById(idWalker);

            if (locacionPaseadorOptional.isPresent()) {
                idLocationWalker = locacionPaseadorOptional.get().getId();
            }
        }
        String name = user.getName();
        String lastName = user.getLastName();

        Owner owner = user.getOwner();
        if (owner != null) {
            idOwner = owner.getId();

            Optional<LocationOwner> locacionPropietarioOptional = locationOwnerRepository.findById(idOwner);
            if (locacionPropietarioOptional.isPresent()) {
                idlocationOwner = locacionPropietarioOptional.get().getId();
            }
        }

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .idUserType(typeUserId)
                .idWalker(idWalker)
                .idOwner(idOwner)
                .Id(idUser)
                .name(name)
                .lastname(lastName)
                .idLocationWalker(idLocationWalker)
                .idLocationOwner(idlocationOwner)
                .build();
    }
}
