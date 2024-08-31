package com.example.demo.domain.serviceImpl.Owner;

import com.example.demo.application.exceptions.OwnerExceptions.Exist.OwnerExistException;
import com.example.demo.application.exceptions.OwnerExceptions.NotFound.OwnerNotFoundException;
import com.example.demo.application.exceptions.UserExceptions.NotFound.UserNotFoundException;
import com.example.demo.application.service.OwnerService.OwnerService;
import com.example.demo.domain.entity.owner.Owner;
import com.example.demo.domain.entity.user.User;
import com.example.demo.domain.repository.Owner.OwnerRepository;
import com.example.demo.domain.repository.User.UserRepository;

import com.example.demo.application.service.FotoService.UploadFilesService;
import com.example.demo.infrastructure.web.projection.classBased.OwnerDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.OwnerProjection;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final UserRepository userRepository;
    private final OwnerRepository ownerRepository;
    private final UploadFilesService uploadFilesService;

    // Servicio para registrar Owner
    @Override
    public OwnerDTO registerOwnerService (OwnerDTO ownerDTO) {
        User user = userRepository.findById(ownerDTO.getIdUser())
                .orElseThrow(()-> new UserNotFoundException("Id del usuario no encontrado"));

        // Lógica para verificar si el usuario ya tiene un propietario asignado
        if (ownerRepository.existsByUserId(user)){
            throw new OwnerExistException("Este usuario ya tiene una cuenta como propietario");
        }
        // Realizando la inserción de datos
        Owner owner = Owner.builder()
                .userId(user)
                .calification(ownerDTO.getCalification())
                .comment(ownerDTO.getComment())
                .walkingPreferences(ownerDTO.getWalkingPreferences())
                .money(ownerDTO.getMoney())
                .availability(ownerDTO.getAvailability())
                .location(ownerDTO.getLocation())
                .build();

        owner = ownerRepository.save(owner);

        // Personalizando respuesta
        Integer idUser = owner.getUserId().getId();
        Integer idTypeUSer = owner.getUserId().getTypeUserId().getId();
        Integer idOwner = owner.getId();
        String name = owner.getUserId().getName();

        return OwnerDTO.builder()
                .idUser(idUser)
                .idTypeUser(idTypeUSer)
                .idOwner(idOwner)
                .name(name)
                .calification(ownerDTO.getCalification())
                .comment(ownerDTO.getComment())
                .walkingPreferences(ownerDTO.getWalkingPreferences())
                .money(ownerDTO.getMoney())
                .availability(ownerDTO.getAvailability())
                .location(ownerDTO.getLocation())
                .build();
    }


    // Servicio para editar un propietario
    @Override
    public OwnerDTO editOwnerService(Integer id, OwnerDTO ownerDTO) {
        Owner existingOwner = ownerRepository.findById(id)
                .orElseThrow(()-> new OwnerNotFoundException("Propietario no encontrado "));

        User user = existingOwner.getUserId();
        if (ownerDTO.getIdUser() != null){
            user = userRepository.findById(ownerDTO.getIdUser())
                    .orElseThrow(()-> new UserNotFoundException("Id de usuario no encontrado"));
        }

        if (ownerDTO.getCalification() != null) {
            existingOwner.setCalification(ownerDTO.getCalification());
        }
        if (ownerDTO.getComment() != null) {
            existingOwner.setComment(ownerDTO.getComment());
        }
        if (ownerDTO.getWalkingPreferences() != null) {
            existingOwner.setWalkingPreferences(ownerDTO.getWalkingPreferences());
        }
        if (ownerDTO.getMoney() != null) {
            existingOwner.setMoney(ownerDTO.getMoney());
        }
        if (ownerDTO.getAvailability() != null) {
            existingOwner.setAvailability(ownerDTO.getAvailability());
        }
        if (ownerDTO.getLocation() != null) {
            existingOwner.setLocation(ownerDTO.getLocation());
        }

        existingOwner = ownerRepository.save(existingOwner);

        // Personalizando respuesta
        Integer idUser = existingOwner.getUserId().getId();
        Integer idTypeUser = existingOwner.getUserId().getTypeUserId().getId();
        Integer idOwner = existingOwner.getId();
        String name = existingOwner.getUserId().getName();

        return OwnerDTO.builder()
                .idUser(idUser)
                .idTypeUser(idTypeUser)
                .idOwner(idOwner)
                .name(name)
                .calification(existingOwner.getCalification()) // Usar el valor actualizado del propietarioExistente
                .comment(existingOwner.getComment())
                .walkingPreferences(existingOwner.getWalkingPreferences())
                .money(existingOwner.getMoney())
                .availability(existingOwner.getAvailability())
                .location(existingOwner.getLocation())// Usar el valor actualizado del propietarioExistente
                // Agregar los demás campos actualizados de manera similar
                .build();
    }


    // Implementación de servicio para llamar a un dueño de mascota en función de su ID
    @Override
    public Optional<OwnerProjection> findOwnerByIdService(Integer id) {
        return ownerRepository.findProjectedById(id);
    }

    @Override
    @Transactional
    public boolean deleteOwnerByIdService(Integer id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new OwnerNotFoundException("Id del propietario inexistente: " + id));

        // Desasociar el owner del user
        User user = owner.getUserId();
        if (user != null) {
            user.setOwner(null);
            userRepository.save(user);
        }

        // Eliminar el owner
        ownerRepository.delete(owner);

        return !ownerRepository.existsById(id);
    }

    // Implementación del servicio para mostrar a todos los dueños de las mascotas
    @Override
    public List<OwnerProjection> getAvailableOwnersService() {
        return ownerRepository.findAllProjectedBy();
    }

    @Override
    public void updateFotoOwnerService(Integer id, MultipartFile foto) throws Exception {
        Owner propietario = ownerRepository.findById(id)
                .orElseThrow(() -> new OwnerNotFoundException("Propietario no encontrado"));

        try {
            String rutaFoto = "directorio/owner/" + id + "/foto"; // Ruta donde se guardará la foto
            String respuesta = uploadFilesService.handleFileUpload(foto, rutaFoto);

            // Actualizar el campo de la foto en la entidad Paseador
            propietario.setFoto(respuesta); // Guardar la ruta o identificador de la foto en la entidad
            ownerRepository.save(propietario);
        } catch (Exception e) {
            throw new Exception("Error al actualizar la foto del propietario: " + e.getMessage());
        }
    }
}
