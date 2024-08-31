package com.example.demo.infrastructure.web.controller;

import com.example.demo.application.exceptions.TransactionExceptions.Insufficient.InsufficientMoneyException;
import com.example.demo.application.exceptions.TransactionExceptions.NotFound.StateTransactionNotFoundException;
import com.example.demo.application.exceptions.TransactionExceptions.NotFound.TypeTransactionNotFoundException;
import com.example.demo.application.service.TransactionService.PayService;
import com.example.demo.infrastructure.web.projection.classBased.PayRequestDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/pay")
@RequiredArgsConstructor
public class PayController {

    private final PayService payService;

    @PostMapping("/process")
    public ResponseEntity<String> procesarPago(@RequestBody @Valid PayRequestDTO pagoRequest) {
        try {
            payService.processPay(pagoRequest);
            return ResponseEntity.ok("your payment was successful");
        } catch (InsufficientMoneyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Saldo insuficiente para realizar el pago");
        } catch (EntityNotFoundException | StateTransactionNotFoundException | TypeTransactionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró algún elemento necesario para el pago");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al procesar el pago");
        }
    }
}
