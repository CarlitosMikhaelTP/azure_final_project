package com.example.demo.infrastructure.web.controller;

import com.example.demo.application.service.TransactionService.TransactionService;
import com.example.demo.infrastructure.web.projection.classBased.TransactionDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.TransactionProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    // Endpoint para registrar Transaction
    @PostMapping("/register")
    public ResponseEntity<TransactionDTO> registerTransaction(@RequestBody TransactionDTO transactionDTO){
        TransactionDTO registeredTransaction = transactionService.registerTransactionService(transactionDTO);
        return ResponseEntity.ok(registeredTransaction);
    }

    // Endpoint para editar Transaction
    @PutMapping("/edit/{id}")
    public ResponseEntity<TransactionDTO> editTransaction(
            @PathVariable("id") Integer id,
            @RequestBody TransactionDTO transactionDTO
    ){
        TransactionDTO updatedTransaction = transactionService.editTransaction(id, transactionDTO);
        return ResponseEntity.ok(updatedTransaction);
    }

    // Endpoint para listar a todos las transacciones usando proyecciones
    @GetMapping("/findAllTransaction")
    public List<TransactionProjection> findAllTransaction(){
        return transactionService.showAllTransactionService(); // Obtiene todos las transacciones con la proyección
    }

    // Endpoint para listar una transaccion por un ID
    @GetMapping("/findTransactionById/{id}")
    public Optional<TransactionProjection> findTransactionById(@PathVariable Integer id){
        return transactionService.showTransactionByIdService(id);
    }

    // Endpoint para eliminar una transaccion por su ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTransactionById(@PathVariable("id") Integer id){
        boolean deleted = transactionService.deleteTransactionByIdService(id);

        if (deleted){
            return ResponseEntity.ok("Transaction was deleted successfull");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Was not possible delele the transaction !!");
        }
    }
}
