package com.example.demo.domain.serviceImpl.Owner;

import com.example.demo.application.exceptions.WalkerExceptions.Exist.LocationWalkerExistException;
import com.example.demo.application.exceptions.WalkerExceptions.NotFound.WalkerNotFoundException;
import com.example.demo.application.exceptions.OwnerExceptions.NotFound.LocationOwnerNotFoundException;
import com.example.demo.application.exceptions.OwnerExceptions.NotFound.OwnerNotFoundException;
import com.example.demo.application.service.OwnerService.LocationOwnerService;
import com.example.demo.domain.entity.owner.LocationOwner;
import com.example.demo.domain.entity.owner.Owner;
import com.example.demo.domain.repository.Owner.LocationOwnerRepository;
import com.example.demo.domain.repository.Owner.OwnerRepository;
import com.example.demo.infrastructure.web.projection.classBased.LocationOwnerDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.LocationOwnerProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationOwnerServiceImpl implements LocationOwnerService {

    private final LocationOwnerRepository locationOwnerRepository;
    private final OwnerRepository ownerRepository;

    // Servicio para registrar la locación de owner
    @Override
    public LocationOwnerDTO registerLocationOwnerService(LocationOwnerDTO locationOwnerDTO) {
        Owner owner = ownerRepository.findById(locationOwnerDTO.getId())
                .orElseThrow(() -> new OwnerNotFoundException("Id del paseador no encontrado"));
        // Lógica para verificar si la locación del paseador ya tiene un ID de paseador asignado
        if (locationOwnerRepository.existsByOwnerId(owner)) {
            throw new LocationWalkerExistException("Esta locación ya tiene un Id de paseador vinculado");
        }

        LocationOwner locationOwner = LocationOwner.builder()
                .latitude(locationOwnerDTO.getLatitude())
                .lenght(locationOwnerDTO.getLenght())
                .ownerId(owner)
                .build();

        // Guardando la locación del paseador en la base de datos usando el repositorio
        locationOwner = locationOwnerRepository.save(locationOwner);

        // Personalizando respuesta
        BigDecimal lenght = locationOwner.getLenght();
        BigDecimal latitude = locationOwner.getLatitude();
        Integer idOwner = locationOwner.getOwnerId().getId();
        Integer idUser = locationOwner.getOwnerId().getUserId().getId();
        Integer typeUser = locationOwner.getOwnerId().getUserId().getTypeUserId().getId();
        String nameOwner = locationOwner.getOwnerId().getUserId().getName();

        return LocationOwnerDTO.builder()
                .lenght(lenght)
                .latitude(latitude)
                .id(idOwner)
                .idUser(idUser)
                .idTypeUser(typeUser)
                .name(nameOwner)
                .build();
    }

    // Servicio para editar la locación del paseador por su ID
    @Override
    public LocationOwnerDTO editLocationOwnerService(Integer id, LocationOwnerDTO locationOwnerDTO) {
        LocationOwner existingOwnerLocation = locationOwnerRepository.findById(id)
                .orElseThrow(() -> new LocationOwnerNotFoundException("Locacion de paseador no encontrada"));

        Owner owner = existingOwnerLocation.getOwnerId();
        if (locationOwnerDTO.getId() != null) {
            owner = ownerRepository.findById(locationOwnerDTO.getId())
                    .orElseThrow(() -> new WalkerNotFoundException("Id del paseador no encontrado"));
        }
        if (locationOwnerDTO.getLatitude() != null){
            existingOwnerLocation.setLatitude(locationOwnerDTO.getLatitude());
        }
        if (locationOwnerDTO.getLenght() != null){
            existingOwnerLocation.setLenght(locationOwnerDTO.getLenght());
        }

        existingOwnerLocation = locationOwnerRepository.save(existingOwnerLocation);

        // Personalizando respuesta
        BigDecimal OwnerLocationLenght = existingOwnerLocation.getLenght();
        BigDecimal OwnerLocationLatitude = existingOwnerLocation.getLatitude();
        Integer idOwner = existingOwnerLocation.getOwnerId().getId();
        Integer idUser = existingOwnerLocation.getOwnerId().getUserId().getId();
        Integer typeUser = existingOwnerLocation.getOwnerId().getUserId().getTypeUserId().getId();
        String nombres = existingOwnerLocation.getOwnerId().getUserId().getName();

        return LocationOwnerDTO.builder()
                .lenght(OwnerLocationLenght)
                .latitude(OwnerLocationLatitude)
                .id(idOwner)
                .idUser(idUser)
                .idTypeUser(typeUser)
                .name(nombres)
                .build();
    }

    // Servicio para listar la locación de los walkers registrados
    @Override
    public List<LocationOwnerProjection> showAllLocationsOwnersService() {
        return locationOwnerRepository.findAllProjectedBy();
    }



    // Servicio para listar una locación de paseador por su ID
    @Override
    public Optional<LocationOwnerProjection> findLocationOwnerByIdService(Integer id) {
        return locationOwnerRepository.findProjectedById(id);
    }

    // Servicio para eliminar la locación de paseador
    @Override
    public boolean deleteLocationOwnerByIdService(Integer id) {
        LocationOwner locationOwner = locationOwnerRepository.findById(id)
                .orElseThrow(() -> new LocationOwnerNotFoundException("Id de la locación del dueño no encontrada"));
        locationOwnerRepository.delete(locationOwner);
        System.out.println("Se eliminió la locación del propipetario con el ID: " + id);
        return true;
    }
}
