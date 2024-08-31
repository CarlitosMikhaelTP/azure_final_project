package com.example.demo.domain.serviceImpl.Walker;

import com.example.demo.application.exceptions.UserExceptions.exist.UserExistException;
import com.example.demo.application.exceptions.WalkExceptions.Exists.WalkExistException;
import com.example.demo.application.exceptions.WalkerExceptions.Exist.WalkerExistException;
import com.example.demo.application.exceptions.WalkerExceptions.NotFound.CategoryNotFoundException;
import com.example.demo.application.exceptions.WalkerExceptions.NotFound.WalkerNotFoundException;
import com.example.demo.application.exceptions.UserExceptions.NotFound.UserNotFoundException;
import com.example.demo.application.service.WalkerService.WalkerService;
import com.example.demo.domain.entity.walker.Category;
import com.example.demo.domain.entity.walker.Walker;
import com.example.demo.domain.entity.user.User;
import com.example.demo.domain.repository.Owner.OwnerRepository;
import com.example.demo.domain.repository.Walker.CategoryRepository;
import com.example.demo.domain.repository.Walker.WalkerRepository;
import com.example.demo.domain.repository.User.UserRepository;

import com.example.demo.application.service.FotoService.UploadFilesService;
import com.example.demo.infrastructure.web.projection.classBased.WalkerDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.WalkerProjection;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalkerServiceImpl implements WalkerService {


    private final UserRepository userRepository;
    private final WalkerRepository walkerRepository;
    private final CategoryRepository categoryRepository;
    private final UploadFilesService uploadFilesService;


    // Servicio para registrar Walker
    @Override
    public WalkerDTO registerWalkerService(WalkerDTO walkerDTO) {

        User user = userRepository.findById(walkerDTO.getIdUser())
                .orElseThrow(() -> new UserNotFoundException("Id de usuario no encontrado"));

        // Lógica para verificar si el usuario ya tiene un paseador asignado
        if (walkerRepository.existsByUserId (user)) {
            throw new WalkerExistException("Este paseador ya tiene una cuenta como paseador");
        }
        // Lógica para verificar si el usuario ya tiene una cuenta asignada
        //if (userRepository.existsById(user)) {
          //  throw new UserExistException("A user already exists for this count!");
       // }

        Category category = categoryRepository.findById(walkerDTO.getIdCategory())
                .orElseThrow(() -> new CategoryNotFoundException("Id de la categoría no encontrado"));

        // Implementar validaciones necesarias después
        Walker walker = Walker.builder()
                .calification(walkerDTO.getCalification())
                .description(walkerDTO.getDescription())
                .experience(walkerDTO.getExperience())
                .prefer_race(walkerDTO.getPrefer_race())
                .location(walkerDTO.getLocation())
                .number_walk(walkerDTO.getNumber_walk())
                .cost(walkerDTO.getCost())
                .money(walkerDTO.getMoney())
                .availability(walkerDTO.getAvailability())
                .categoryId(category)
                .userId(user)
                .build();

        // Guardando al paseador en la base de datos usando el repositorio
        walker = walkerRepository.save(walker);
        // Personalizando respuesta
        Integer idUser = walker.getUserId().getId();
        Integer idTypeUser = walker.getUserId().getTypeUserId().getId();
        Integer idWalker = walker.getId();
        String name = walker.getUserId().getName();

        return WalkerDTO.builder()
                .idUser(idUser)
                .idTypeUser(idTypeUser)
                .id(idWalker)
                .name(name)
                .idCategory(walkerDTO.getIdCategory())
                .calification(walkerDTO.getCalification())
                .number_walk(walkerDTO.getNumber_walk())
                .description(walkerDTO.getDescription())
                .experience(walkerDTO.getExperience())
                .prefer_race(walkerDTO.getPrefer_race())
                .location(walkerDTO.getLocation())
                .cost(walkerDTO.getCost())
                .money(walkerDTO.getMoney())
                .availability(walkerDTO.getAvailability())
                .build();
    }

    @Override
    public void editFotoWalkerService(Integer id, MultipartFile foto) throws Exception {
        Walker walker = walkerRepository.findById(id)
                .orElseThrow(() -> new WalkerNotFoundException("Paseador no encontrado"));

        try {
            String rutaFoto = "directorio/walker/" + id + "/foto"; // Ruta donde se guardará la foto
            String respuesta = uploadFilesService.handleFileUpload(foto, rutaFoto);

            //Actualizar el campo de la foto en la entidad Paseador
            walker.setFoto(respuesta); // Guardar la ruta o identificador de la foto en la entidad
            walkerRepository.save(walker);
        } catch (Exception e) {
            throw new Exception("Error al actualizar la foto del paseador: " + e.getMessage());
        }
    }


    // Servicio para editar walker
    @Override
    public WalkerDTO editWalkerService(Integer id, WalkerDTO walkerDTO) {
        Walker existingWalker = walkerRepository.findById(id)
                .orElseThrow(() -> new WalkerNotFoundException("Paseador no encontrado"));

        User user = existingWalker.getUserId();
        if (walkerDTO.getIdUser() != null) {
            user = userRepository.findById(walkerDTO.getIdUser())
                    .orElseThrow(() -> new UserNotFoundException("Id de usuario paseador no encontrado"));
        }
        Category category = existingWalker.getCategoryId();
        if (walkerDTO.getIdCategory() != null) {
            category = categoryRepository.findById(walkerDTO.getIdCategory())
                    .orElseThrow(() -> new CategoryNotFoundException("Id de la categoría no encontrado"));
        }

        // Verifica cada campo antes de actualizar
        if (walkerDTO.getCalification() != null) {
            existingWalker.setCalification(walkerDTO.getCalification());
        }
        if (walkerDTO.getDescription() != null) {
            existingWalker.setDescription(walkerDTO.getDescription());
        }
        if (walkerDTO.getExperience() != null) {
            existingWalker.setExperience(walkerDTO.getExperience());
        }
        if (walkerDTO.getPrefer_race() != null){
            existingWalker.setPrefer_race(walkerDTO.getPrefer_race());
        }
        if (walkerDTO.getLocation() != null) {
            existingWalker.setLocation(walkerDTO.getLocation());
        }
        if (walkerDTO.getNumber_walk() != null) {
            existingWalker.setNumber_walk(walkerDTO.getNumber_walk());
        }
        if (walkerDTO.getCost() != null) {
            existingWalker.setCost(walkerDTO.getCost());
        }
        if (walkerDTO.getMoney() != null) {
            existingWalker.setMoney(walkerDTO.getMoney());
        }
        if (walkerDTO.getAvailability() != null) {
            existingWalker.setAvailability(walkerDTO.getAvailability());
        }

        existingWalker = walkerRepository.save(existingWalker);
        // Personalizando respuesta
        Integer idUser = existingWalker.getUserId().getId();
        Integer idTypeUser = existingWalker.getUserId().getTypeUserId().getId();
        Integer idWalker = existingWalker.getId();
        String name = existingWalker.getUserId().getName();

        // Devuelve la respuesta con los campos actualizados
        return WalkerDTO.builder()
                .idUser(existingWalker.getUserId().getId())
                .idTypeUser(existingWalker.getUserId().getTypeUserId().getId())
                .id(existingWalker.getId())
                .name(existingWalker.getUserId().getName())
                .idCategory(walkerDTO.getIdCategory())
                .calification(existingWalker.getCalification())
                .number_walk(existingWalker.getNumber_walk())
                .description(existingWalker.getDescription())
                .experience(existingWalker.getExperience())
                .prefer_race(existingWalker.getPrefer_race())
                .location(existingWalker.getLocation())
                .cost(existingWalker.getCost())
                .money(existingWalker.getMoney())
                .availability(existingWalker.getAvailability())
                .build();
    }

    // Servicio para mostrar a los walker registrados
    @Override
    public List<WalkerProjection> showAllWalkersService() {
        return walkerRepository.findAllProjectedBy();
    }


    // Servicio para listar un registro de paseador por ID
    @Override
    public Optional<WalkerProjection> findWalkerByIdService(Integer id) {
        return walkerRepository.findProjectedById(id);
    }


    // Servicio para eliminar la locación de un paseador
    @Override
    public boolean deleteWalkerByIdService(Integer id) {
        Walker walker = walkerRepository.findById(id)
                .orElseThrow(()-> new WalkerNotFoundException("Id del paseador inexistente"+ id));
        walkerRepository.delete(walker);
        System.out.println("Se eliminó al paseador con el ID: "+ id);
        return true;

    }

}

