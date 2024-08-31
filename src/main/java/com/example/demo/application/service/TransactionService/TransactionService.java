package com.example.demo.application.service.TransactionService;

import com.example.demo.infrastructure.web.projection.classBased.TransactionDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.TransactionProjection;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    // Definiendo interfaz para realizar el registro de transacciones
    TransactionDTO registerTransactionService(TransactionDTO transactionDTO);

    // Definiendo interfaz para realizar la edición de transacciones
    TransactionDTO editTransaction(Integer id, TransactionDTO transactionDTO);

    // Definiendo Interfaz para mostrar las transacciones registradas
    List<TransactionProjection> showAllTransactionService();

    // Definiendo interfaz para mostrar una transaccion por su ID
    Optional<TransactionProjection> showTransactionByIdService(Integer id);

    // Definiendo interfaz para eliminar una transacción por su ID
    boolean deleteTransactionByIdService(Integer id);
}
