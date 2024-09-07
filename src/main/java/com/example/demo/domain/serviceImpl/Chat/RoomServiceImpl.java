package com.example.demo.domain.serviceImpl.Chat;

import com.example.demo.application.exceptions.BookingExceptions.NotFound.BookingNotFoundException;
import com.example.demo.application.exceptions.ChatExceptions.Exist.RoomExistException;
import com.example.demo.application.exceptions.ChatExceptions.NotFound.ChatNotFoundException;
import com.example.demo.application.exceptions.ChatExceptions.NotFound.RoomNotFoundException;
import com.example.demo.application.exceptions.ChatExceptions.NotFound.TypeMessageNotFoundException;
import com.example.demo.application.exceptions.ChatExceptions.NotFound.TypeRoomNotFoundException;
import com.example.demo.application.exceptions.UserExceptions.NotFound.UserNotFoundException;
import com.example.demo.application.service.ChatService.ChatService;
import com.example.demo.application.service.ChatService.RoomService;
import com.example.demo.domain.entity.booking.Booking;
import com.example.demo.domain.entity.chat.Chat;
import com.example.demo.domain.entity.chat.Room;
import com.example.demo.domain.entity.chat.TypeMessage;
import com.example.demo.domain.entity.chat.TypeRoom;
import com.example.demo.domain.entity.user.User;
import com.example.demo.domain.repository.Booking.BookingRepository;
import com.example.demo.domain.repository.Chat.ChatRepository;
import com.example.demo.domain.repository.Chat.RoomRepository;
import com.example.demo.domain.repository.Chat.TypeMessageRepository;
import com.example.demo.domain.repository.Chat.TypeRoomRepository;
import com.example.demo.domain.repository.User.UserRepository;
import com.example.demo.infrastructure.web.projection.classBased.ChatDTO;
import com.example.demo.infrastructure.web.projection.classBased.RoomDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.ChatProjection;
import com.example.demo.infrastructure.web.projection.interfaceBased.RoomProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final TypeRoomRepository typeRoomRepository;

    // Implementando el servicio para realizar las salas
    @Override
    public RoomDTO registerRoomService(RoomDTO roomDTO) {

        Booking booking = bookingRepository.findById(roomDTO.getBookingId())
                .orElseThrow(()-> new BookingNotFoundException("Id de la reserva no encontrado"));

        if (roomRepository.existsByBooking(booking)){
            throw new RoomExistException("Esta sala ya cuenta con una reserva asociada");
        }
        TypeRoom typeRoom = typeRoomRepository.findById(roomDTO.getTypeRoomId())
                .orElseThrow(()-> new TypeRoomNotFoundException("Id del tipo de sala no encontrado"));

        Room room = Room.builder()
                .booking(booking)
                .typeRoom(typeRoom)
                .date(roomDTO.getDate())
                .build();

        // Guardando los cambios haciendo uso del repositorio
        room = roomRepository.save(room);

        return roomDTO.builder()
                .id(room.getId())
                .typeRoomId(room.getTypeRoom().getId())
                .bookingId(room.getBooking().getId())
                .date(room.getDate())
                .build();
    }

    @Override
    public RoomDTO editRoomService(Integer id, RoomDTO roomDTO) {

        Room roomExists = roomRepository.findById(id)
                .orElseThrow(()-> new RoomNotFoundException("Id de la sala no encontrada."));

        Booking booking = roomExists.getBooking();
        if (roomDTO.getBookingId() != null){
            booking = bookingRepository.findById(roomDTO.getBookingId())
                    .orElseThrow(()-> new BookingNotFoundException("Id de la reserva no encontrado"));
        }
        TypeRoom typeRoom = roomExists.getTypeRoom();
        if (roomDTO.getTypeRoomId() != null){
            typeRoom =  typeRoomRepository.findById(roomDTO.getTypeRoomId())
                    .orElseThrow(()-> new TypeMessageNotFoundException("Id del tipo de mensaje no encontrado"));
        }

        if (roomDTO.getDate() != null){
            roomExists.setDate(roomDTO.getDate());
        }
        // Guardando campos
        roomExists = roomRepository.save(roomExists);

        return roomDTO.builder()
                .id(roomExists.getId())
                .typeRoomId(roomExists.getTypeRoom().getId())
                .bookingId(roomExists.getBooking().getId())
                .date(roomExists.getDate())
                .build();
    }

    @Override
    public List<RoomProjection> showAllRoomService() {
        return roomRepository.findAllProjectedBy();
    }

    @Override
    public Optional<RoomProjection> showRoomByIdService(Integer id) {
        return roomRepository.findProjectedById(id);
    }

    @Override
    public boolean deleteRoomById(Integer id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(()-> new RoomNotFoundException("Id del chat no encontrado."));
        roomRepository.delete(room);
        System.out.println("Se eliminó correctamente la sala");
        return true;
    }
}
