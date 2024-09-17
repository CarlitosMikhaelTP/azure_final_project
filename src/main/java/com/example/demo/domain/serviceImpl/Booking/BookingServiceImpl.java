package com.example.demo.domain.serviceImpl.Booking;

import com.example.demo.application.exceptions.WalkerExceptions.NotFound.WalkerNotFoundException;
import com.example.demo.application.exceptions.BookingExceptions.NotFound.BookingNotFoundException;
import com.example.demo.application.exceptions.OwnerExceptions.NotFound.OwnerNotFoundException;
import com.example.demo.application.service.BookingService.BookingService;
import com.example.demo.domain.entity.booking.Booking;
import com.example.demo.domain.entity.walker.Walker;
import com.example.demo.domain.entity.owner.Owner;
import com.example.demo.domain.repository.Walker.WalkerRepository;
import com.example.demo.domain.repository.Booking.BookingRepository;
import com.example.demo.domain.repository.Owner.OwnerRepository;
import com.example.demo.infrastructure.web.projection.classBased.BookingDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.BookingProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final OwnerRepository ownerRepository;
    private final WalkerRepository walkerRepository;

    @Override
    public BookingDTO registerBookingService(BookingDTO bookingDTO) {
        Walker walker = walkerRepository.findById(bookingDTO.getWalkerId())
                .orElseThrow(()-> new WalkerNotFoundException("Id del paseador no encontrado"));
        Owner owner = ownerRepository.findById(bookingDTO.getOwnerId())
                .orElseThrow(()-> new OwnerNotFoundException("Id del propietario no encontrado"));
        // Lógica para verificar si el pasedor tiene activado su campo de "availability"
        if (!walker.getAvailability()){ // evalua si el paseador está no disponible es decir si está como false, si está como false entonces lanza l aexcepción
            throw new WalkerNotFoundException("El paseador no está habilitado para esta funcionalidad");
        }
        Booking booking = Booking.builder()
                .walkerId(walker)
                .ownerId(owner)
                .cost(bookingDTO.getCost())
                .date(bookingDTO.getDate())
                .duration(bookingDTO.getDuration())
                .comment(bookingDTO.getComment())
                .placeWalk(bookingDTO.getPlaceWalk())
                .meetingPoint(bookingDTO.getMeetingPoint())
                .build();

        // Guardando los cambios haciendo uso del repositorio
        booking = bookingRepository.save(booking);
        Integer IdBooking = booking.getId();
        Integer IdOwner = booking.getOwnerId().getId();
        Integer IdWalker = booking.getWalkerId().getId();
        Double Cost = booking.getCost();

        return BookingDTO.builder()
                .id(IdBooking)
                .ownerId(IdOwner)
                .walkerId(IdWalker)
                .cost(Cost)
                .date(bookingDTO.getDate())
                .duration(bookingDTO.getDuration())
                .comment(bookingDTO.getComment())
                .meetingPoint(bookingDTO.getMeetingPoint())
                .placeWalk(bookingDTO.getPlaceWalk())
                .build();
    }

    @Override
    public BookingDTO editBookingService(Integer id, BookingDTO bookingDTO) {
        Booking bookingExists = bookingRepository.findById(id)
                .orElseThrow(()-> new BookingNotFoundException("Id de la reserva no encontrada"));
        Walker walker = bookingExists.getWalkerId();
        if (bookingDTO.getWalkerId() != null){
            walker = walkerRepository.findById(bookingDTO.getWalkerId())
                    .orElseThrow(()-> new WalkerNotFoundException("Id del paseador no ecnontrado"));
        }
        Owner owner = bookingExists.getOwnerId();
        if (bookingDTO.getOwnerId() != null){
            owner = ownerRepository.findById(bookingDTO.getOwnerId())
                    .orElseThrow(()-> new OwnerNotFoundException("Id del propietario no encontrado"));
        }
        if (bookingDTO.getCost() != null){
            bookingExists.setCost(bookingDTO.getCost());
        }
        if (bookingDTO.getDate() != null){
            bookingExists.setDate(bookingDTO.getDate());
        }
        if (bookingDTO.getDuration() != null){
            bookingExists.setDuration(bookingDTO.getDuration());
        }
        if (bookingDTO.getComment() != null){
            bookingExists.setComment(bookingDTO.getComment());
        }
        if (bookingDTO.getMeetingPoint() != null){
            bookingExists.setMeetingPoint(bookingDTO.getMeetingPoint());
        }
        if (bookingDTO.getPlaceWalk() != null){
            bookingExists.setPlaceWalk(bookingDTO.getPlaceWalk());
        }
        // Guardando campos
        bookingExists = bookingRepository.save(bookingExists);

        return BookingDTO.builder()
                .id(bookingExists.getId())
                .ownerId(bookingExists.getOwnerId().getId())
                .walkerId(bookingExists.getWalkerId().getId())
                .cost(bookingExists.getCost())
                .date(bookingExists.getDate())
                .duration(bookingExists.getDuration())
                .comment(bookingExists.getComment())
                .meetingPoint(bookingExists.getMeetingPoint())
                .placeWalk(bookingExists.getPlaceWalk())
                .build();
    }

    @Override
    public List<BookingProjection> showAllBookingService() {
        return bookingRepository.findAllProjectedBy();
    }

    @Override
    public Optional<BookingProjection> showBookingByIdService(Integer id) {
        return bookingRepository.findProjectedById(id);
    }

    @Override
    public boolean deleteBookingById(Integer id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(()-> new BookingNotFoundException("Id de la reserva no encontrada."));
        bookingRepository.delete(booking);
        System.out.println("Se eliminó correctamente la reserva");
        return true;
    }
}
