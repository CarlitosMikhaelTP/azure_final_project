package com.example.demo.domain.serviceImpl.Pet;

import com.example.demo.application.exceptions.PetExceptions.NotFound.BadHabitsNotFoundException;
import com.example.demo.application.exceptions.PetExceptions.NotFound.PetNotFounException;
import com.example.demo.application.exceptions.PetExceptions.NotFound.TypePetNotFoundException;
import com.example.demo.application.exceptions.WalkerExceptions.NotFound.WalkerNotFoundException;
import com.example.demo.application.exceptions.OwnerExceptions.NotFound.OwnerNotFoundException;
import com.example.demo.application.service.PetService.PetService;
import com.example.demo.domain.entity.pet.BadHabits;
import com.example.demo.domain.entity.pet.Pet;
import com.example.demo.domain.entity.pet.TypePet;
import com.example.demo.domain.entity.owner.Owner;
import com.example.demo.domain.repository.Pet.BadHabitsRepository;
import com.example.demo.domain.repository.Pet.PetRepository;
import com.example.demo.domain.repository.Owner.OwnerRepository;
import com.example.demo.domain.repository.Pet.TypePetRepository;
import com.example.demo.application.service.FotoService.UploadFilesService;
import com.example.demo.infrastructure.web.projection.classBased.PetDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.PetProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final TypePetRepository typePetRepository;
    private final BadHabitsRepository badHabitsRepository;
    private final UploadFilesService uploadFilesService;


    // Servicio para registrar Pet
    @Override
    public PetDTO registerPetService(PetDTO petDTO) {
        Owner owner = ownerRepository.findById(petDTO.getOwnerId())
                .orElseThrow(() -> new OwnerNotFoundException("Id de dueño de mascota no encontrado"));
        TypePet typePet = typePetRepository.findById(petDTO.getTypePetId())
                .orElseThrow(()-> new TypePetNotFoundException("Id del tipo de mascota no encontrado"));
        BadHabits badHabits = badHabitsRepository.findById(petDTO.getBadHabits())
                .orElseThrow(()-> new BadHabitsNotFoundException("Id del tipo de hábito no encontrado"));
        Pet pet = Pet.builder()
                .owner(owner)
                .typePetId(typePet)
                .badHabits(badHabits)
                .name(petDTO.getName())
                .race(petDTO.getRace())
                .weight(petDTO.getWeight())
                .age(petDTO.getAge())
                .needs(petDTO.getNeeds())
                .build();

        // Guardando a la mascota en la base de datos usando el repositorio
        pet = petRepository.save(pet);
        // Personalizando respuesta
        Integer Id = pet.getId();
        Integer IdTypeUser = pet.getOwner().getUserId().getTypeUserId().getId();
        Integer IdUser = pet.getOwner().getUserId().getId();
        Integer IdOwner = pet.getOwner().getId();
        String NameOwner = pet.getOwner().getUserId().getName();
        String namePet = pet.getName();

        return PetDTO.builder()
                .id(Id)
                .typePetId(IdTypeUser)
                .badHabits(badHabits.getId())
                .name(namePet)
                .typerUserId(IdTypeUser)
                .ownerId(IdOwner)
                .userId(IdUser)
                .nameOwner(NameOwner)
                .race(pet.getRace())
                .weight(pet.getWeight())
                .age(pet.getAge())
                .needs(pet.getNeeds())
                .nameBadHabit(pet.getBadHabits().getName())
                .build();
    }

    @Override
    public void updateFotoPetService(Integer id, MultipartFile foto) throws Exception {
        Pet mascota = petRepository.findById(id)
                .orElseThrow(() -> new WalkerNotFoundException("Mascota no encontrada"));

        try {
            String routeFoto = "directorio/mascotas/" + id + "/foto"; // Ruta donde se guardará la foto
            String answer = uploadFilesService.handleFileUpload(foto, routeFoto);

            // Actualizar el campo de la foto en la entidad Paseador
            mascota.setFoto(answer); // Guardar la ruta o identificador de la foto en la entidad
            petRepository.save(mascota);
        } catch (Exception e) {
            throw new Exception("Error al actualizar la foto de la mascota: " + e.getMessage());
        }
    }

    // Servicio para editar Pet
    @Override
    public PetDTO editPetService(Integer id, PetDTO petDTO) {
        Pet petExists = petRepository.findById(id)
                .orElseThrow(() -> new PetNotFounException("Pet not found"));

        Owner owner = petExists.getOwner();
        if (petDTO.getOwnerId() != null) {
            owner = ownerRepository.findById(petDTO.getOwnerId())
                    .orElseThrow(() -> new OwnerNotFoundException("Id de propietario no encontrado"));
        }
        TypePet typePet = petExists.getTypePetId();
        if (petDTO.getTypePetId() != null) {
            typePet = typePetRepository.findById(petDTO.getTypePetId())
                    .orElseThrow(() -> new TypePetNotFoundException("Id del tipo de mascota no encontrado"));
        }
        BadHabits badHabits = petExists.getBadHabits();
        if (petDTO.getBadHabits() != null){
            badHabits = badHabitsRepository.findById(petDTO.getBadHabits())
                    .orElseThrow(()-> new BadHabitsNotFoundException("Id del tipo de hábitos de mascota no encontrado"));
        }
        // Verificando cada campo antes de actualziar
        if (petDTO.getName() != null){
            petExists.setName(petDTO.getName());
        }
        if (petDTO.getRace() != null){
            petExists.setRace(petDTO.getRace());
        }
        if (petDTO.getWeight() != null){
            petExists.setWeight(petDTO.getWeight());
        }
        if (petDTO.getAge() != null){
            petExists.setAge(petDTO.getAge());
        }
        if (petDTO.getNeeds() != null){
            petExists.setNeeds(petDTO.getNeeds());
        }

        petExists = petRepository.save(petExists);

        // Personalizando respuesta
        Integer IdPet = petExists.getId();
        Integer IdTypeUser = petExists.getOwner().getUserId().getTypeUserId().getId();
        Integer IdUser = petExists.getOwner().getUserId().getId();
        Integer IdOwner = petExists.getOwner().getId();
        String NameOwner = petExists.getOwner().getUserId().getName();
        String NamePet = petExists.getName();

        return PetDTO.builder()
                .id(petExists.getId())
                .typePetId(petExists.getTypePetId().getId())
                .name(petExists.getName())
                .typerUserId(petExists.getOwner().getUserId().getTypeUserId().getId())
                .ownerId(petExists.getOwner().getId())
                .userId(petExists.getOwner().getUserId().getId())
                .nameOwner(petExists.getOwner().getUserId().getName())
                .race(petExists.getRace())
                .weight(petExists.getWeight())
                .badHabits(petExists.getBadHabits().getId())
                .nameBadHabit(petExists.getBadHabits().getName())
                .age(petExists.getAge())
                .needs(petExists.getNeeds())
                .build();
    }

    // Servicio para mostrar a las mascotas registradas
    @Override
    public List<PetProjection> showAllPetService() {
        return petRepository.findAllProjectedBy();
    }


    // Servicio para listar un registro de mascota por su ID
    @Override
    public Optional<PetProjection> showAllPetService(Integer id) {
        return petRepository.findProjectedById(id);
    }


    // Servicio para eliminar un registro de mascota por su ID
    @Override
    public boolean deletePetById(Integer id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(()-> new PetNotFounException("Id DE LA MASCOTA INEXISTENTE" + id));
        petRepository.delete(pet);
        System.out.println("Se eliminó correcramente a la mascota ");
        return true;
    }
}
