package com.example.demo.domain.serviceImpl.Walk;

import com.example.demo.application.exceptions.WalkExceptions.Exists.WalkExistException;
import com.example.demo.application.exceptions.WalkExceptions.NotFound.WalkNotFoundException;
import com.example.demo.application.exceptions.BookingExceptions.NotFound.BookingNotFoundException;
import com.example.demo.application.exceptions.WalkerExceptions.Exist.WalkerExistException;
import com.example.demo.application.service.WalkService.WalkService;
import com.example.demo.domain.entity.walk.Walk;
import com.example.demo.domain.entity.booking.Booking;
import com.example.demo.domain.repository.Walk.WalkRepository;
import com.example.demo.domain.repository.Booking.BookingRepository;
import com.example.demo.infrastructure.web.projection.classBased.WalkDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.WalkProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalkServiceImpl implements WalkService {

    private final WalkRepository walkRepository;
    private final BookingRepository bookingRepository;
    @Override
    public WalkDTO registerWalkService(WalkDTO walkDTO) {
        Booking booking = bookingRepository.findById(walkDTO.getBookingId())
                .orElseThrow(()-> new BookingNotFoundException("Booking's id not found !"));

        // Verfiicando existencia de un paseo para esta reserva
        if (walkRepository.existsByBookingId(booking)) {
            throw new WalkExistException("A walk already exists for this booking!");
        }
        Walk walk = Walk.builder()
                .bookingId(booking)
                .start(walkDTO.getStart())
                .duration(walkDTO.getDuration())
                .placeWalk(walkDTO.getPlaceWalk())
                .comment(walkDTO.getComment())
                //.calification(walkDTO.getCalification())
                .cost(walkDTO.getCost())
                .build();
        // Guardando los cambios haciendo uso del repositorio de paseo
        walk = walkRepository.save(walk);

        return WalkDTO.builder()
                .id(walk.getId())
                .bookingId(walk.getBookingId().getId())
                .ownerId(walk.getBookingId().getOwnerId().getId())
                .walkerId(walk.getBookingId().getWalkerId().getId())
                .start(walk.getStart())
                .duration(walk.getDuration())
                .placeWalk(walk.getPlaceWalk())
                .comment(walk.getComment())
                //.calification(walkDTO.getCalification())
                .cost(walk.getCost())
                .nameOwner(walk.getBookingId().getOwnerId().getUserId().getName())
                .nameWalker(walk.getBookingId().getWalkerId().getUserId().getName())
                .build();
    }

    @Override
    public WalkDTO editWalkService(Integer id, WalkDTO walkDTO) {

        Walk walkExists = walkRepository.findById(id)
                .orElseThrow(()-> new WalkNotFoundException("Walk's id not found !!"));
        Booking booking = walkExists.getBookingId();
        if (walkDTO.getBookingId() != null){
            booking = bookingRepository.findById(walkDTO.getBookingId())
                    .orElseThrow(()-> new BookingNotFoundException("Booking's id not found !! "));
        }
        if (walkDTO.getStart() != null){
            walkExists.setStart(walkDTO.getStart());
        }
        if (walkDTO.getDuration() != null){
            walkExists.setDuration(walkDTO.getDuration());
        }
        if (walkDTO.getPlaceWalk() != null){
            walkExists.setPlaceWalk(walkDTO.getPlaceWalk());
        }
        if (walkDTO.getComment() != null){
            walkExists.setComment(walkDTO.getComment());
        }
        if (walkDTO.getCost() != null){
            walkExists.setCost(walkDTO.getCost());
        }

        // Guardando actualizacion
        walkExists = walkRepository.save(walkExists);

        return WalkDTO.builder()
                .id(walkExists.getId())
                .bookingId(walkExists.getBookingId().getId())
                .ownerId(walkExists.getBookingId().getOwnerId().getId())
                .walkerId(walkExists.getBookingId().getWalkerId().getId())
                .start(walkExists.getStart())
                .duration(walkExists.getDuration())
                .placeWalk(walkExists.getPlaceWalk())
                .comment(walkExists.getComment())
                //.calification(walkDTO.getCalification())
                .cost(walkExists.getCost())
                .build();
    }

    @Override
    public List<WalkProjection> showAllWalkService() {
        return walkRepository.findAllProjectedBy();
    }

    @Override
    public Optional<WalkProjection> showWalkByIdService(Integer id) {
        return walkRepository.findProjectedById(id);
    }

    @Override
    public boolean deleteWalkByIdService(Integer id) {
        Walk walk = walkRepository.findById(id)
                .orElseThrow(()-> new WalkNotFoundException("Id del paso no encontrado"));
        walkRepository.delete(walk);
        System.out.println("Se eliminó correctamente el paseo");
        return true;
    }
}
