package com.example.demo.infrastructure.web.controller;

import com.example.demo.application.service.BookingService.BookingService;
import com.example.demo.infrastructure.web.projection.classBased.BookingDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.BookingProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    // Endpoint para registrar Booking
    @PostMapping("/register")
    public ResponseEntity<BookingDTO> registerBooking(@RequestBody BookingDTO bookingDTO){
        BookingDTO registeredBooking = bookingService.registerBookingService(bookingDTO);
        return ResponseEntity.ok(registeredBooking);
    }

    // Endpoint para editar Transaction
    @PutMapping("/edit/{id}")
    public ResponseEntity<BookingDTO> editBookingService(
            @PathVariable("id") Integer id,
            @RequestBody BookingDTO bookingDTO
    ){
        BookingDTO updatedBooking = bookingService.editBookingService(id, bookingDTO);
        return ResponseEntity.ok(updatedBooking);
    }

    // Endpoint para listar a todos las transacciones usando proyecciones
    @GetMapping("/findAllBooking")
    public List<BookingProjection> findAllBooking(){
        return bookingService.showAllBookingService(); // Obtiene todos las transacciones con la proyección
    }

    // Endpoint para listar una reserva por un ID
    @GetMapping("/findBookingById/{id}")
    public Optional<BookingProjection> findBookingById(@PathVariable Integer id){
        return bookingService.showBookingByIdService(id);
    }

    // Endpoint para eliminar una reserva por su ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBookingById(@PathVariable("id") Integer id){
        boolean deleted = bookingService.deleteBookingById(id);

        if (deleted){
            return ResponseEntity.ok("The Booking was deleted successfull");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Was not possible delete the Booking");
        }
    }

}
