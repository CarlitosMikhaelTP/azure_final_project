package com.example.demo.application.service.BookingService;

import com.example.demo.infrastructure.web.projection.classBased.BookingDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.BookingProjection;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    // Definiendo interfaz para realizar el registro de booking
    BookingDTO registerBookingService(BookingDTO bookingDTO);

    // Definiendo interfaz para realizar la edición de booking
    BookingDTO editBookingService(Integer id, BookingDTO bookingDTO);

    // Definiendo Interfaz para mostrar las booking registradas
    List<BookingProjection> showAllBookingService();

    // Definiendo interfaz para mostrar una reserva por su ID
    Optional<BookingProjection> showBookingByIdService(Integer id);

    // Definiendo interfaz para eliminar una reserva por su ID
    boolean deleteBookingById(Integer id);
}
