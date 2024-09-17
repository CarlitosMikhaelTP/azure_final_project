package com.example.demo.domain.serviceImpl.Walker;

import com.example.demo.application.exceptions.WalkerExceptions.Exist.LocationWalkerExistException;
import com.example.demo.application.exceptions.WalkerExceptions.NotFound.LocationWalkerNotFoundException;
import com.example.demo.application.exceptions.WalkerExceptions.NotFound.WalkerNotFoundException;
import com.example.demo.application.service.WalkerService.LocationWalkerService;
import com.example.demo.domain.entity.walker.LocationWalker;
import com.example.demo.domain.entity.walker.Walker;
import com.example.demo.domain.repository.Walker.LocationWalkerRepository;
import com.example.demo.domain.repository.Walker.WalkerRepository;
import com.example.demo.infrastructure.web.projection.classBased.LocationWalkerDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.LocationWalkerProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationWalkerServiceImpl implements LocationWalkerService {

    private final LocationWalkerRepository locationWalkerRepository;
    private final WalkerRepository walkerRepository;

    // Servicio para registrar la locación de walker
    @Override
    public LocationWalkerDTO registerLocationWalkerService(LocationWalkerDTO locationWalkerDTO) {
        Walker walker = walkerRepository.findById(locationWalkerDTO.getId())
                .orElseThrow(()-> new WalkerNotFoundException("Id del paseador no encontrado"));
        // Lógica para verificar si el pasedor tiene activado su campo de "availability"
        if (!walker.getAvailability()){ // evalua si el paseador está no disponible es decir si está como false, si está como false entonces lanza l aexcepción
            throw new WalkerNotFoundException("El paseador no está habilitado para esta funcionalidad");
        }

        // Lógica para verificar si la locación del paseador ya tiene un ID de paseador asignado
        if (locationWalkerRepository.existsByWalkerId(walker)){
            throw new LocationWalkerExistException("Esta locación ya tiene un Id de paseador vinculado");
        }

        LocationWalker locationWalker = LocationWalker.builder()
                .latitude(locationWalkerDTO.getLatitude())
                .lenght(locationWalkerDTO.getLenght())
                .walkerId(walker)
                .build();

        // Guardando la locación del paseador en la base de datos usando el repositorio
        locationWalker = locationWalkerRepository.save(locationWalker);
        // Personalizando respuesta
        BigDecimal lenght = locationWalker.getLenght();
        BigDecimal latitude = locationWalker.getLatitude();
        Integer idWalker = locationWalker.getWalkerId().getId();
        Integer idUser = locationWalker.getWalkerId().getUserId().getId();
        Integer idTypeUser = locationWalker.getWalkerId().getUserId().getTypeUserId().getId();
        String name = locationWalker.getWalkerId().getUserId().getName();
        Integer idLocationWalker = locationWalker.getId();
        String lastName = locationWalker.getWalkerId().getUserId().getLastName();

        return LocationWalkerDTO.builder()
                .id(idLocationWalker)
                .lenght(lenght)
                .latitude(latitude)
                .idWalker(idWalker)
                .idUser(idUser)
                .idTypeUser(idTypeUser)
                .name(name)
                .lastname(lastName)
                .build();
    }


    // Servicio para editar la locación del paseador por su ID
    @Override
    public LocationWalkerDTO editLocationWalkerService(Integer id, LocationWalkerDTO locationWalkerDTO) {
        LocationWalker locationWalkerExistente = locationWalkerRepository.findById(id)
                .orElseThrow(()-> new LocationWalkerNotFoundException("Locacion de paseador no encontrada"));

        Walker walker = locationWalkerExistente.getWalkerId();
        if (locationWalkerDTO.getId() != null){
            walker = walkerRepository.findById(locationWalkerDTO.getIdWalker())
                    .orElseThrow(()-> new WalkerNotFoundException("Id del paseador no encontrado"));
        }
        if (locationWalkerDTO.getLatitude() != null){
            locationWalkerExistente.setLatitude(locationWalkerDTO.getLatitude());
        }
        if (locationWalkerDTO.getLenght() != null){
            locationWalkerExistente.setLenght(locationWalkerDTO.getLenght());
        }

        locationWalkerExistente = locationWalkerRepository.save(locationWalkerExistente);

        return LocationWalkerDTO.builder()
                .id(locationWalkerExistente.getId())
                .lenght(locationWalkerExistente.getLenght())
                .latitude(locationWalkerExistente.getLatitude())
                .idWalker(locationWalkerExistente.getWalkerId().getId())
                .idUser(locationWalkerExistente.getWalkerId().getUserId().getId())
                .idTypeUser(locationWalkerExistente.getWalkerId().getUserId().getTypeUserId().getId())
                .name(locationWalkerExistente.getWalkerId().getUserId().getName())
                .lastname(locationWalkerExistente.getWalkerId().getUserId().getLastName())
                .build();

    }


    // Servicio para listar la locación de los walker registrados
    @Override
    public List<LocationWalkerProjection> showAllWalkersService() {
        return locationWalkerRepository.findAllProjectedBy();
    }


    // Servicio para listar una locación de paseador por su ID
    @Override
    public Optional<LocationWalkerProjection> findLocationWalkerByIdService(Integer id) {
        return locationWalkerRepository.findProjectedById(id);
    }


    // Servicio para eliminar la locación de paseador
    @Override
    public boolean deleteLocationWalkerByIdService(Integer id) {
        LocationWalker locationWalker = locationWalkerRepository.findById(id)
                .orElseThrow(()-> new LocationWalkerNotFoundException("Id de la locación del paseador no encontrada"));
        locationWalkerRepository.delete(locationWalker);
        System.out.println("Se eliminió la locación del paseador con el ID: "+ id);
        return true;
    }
}
