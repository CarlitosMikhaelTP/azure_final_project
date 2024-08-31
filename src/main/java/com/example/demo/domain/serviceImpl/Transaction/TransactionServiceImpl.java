package com.example.demo.domain.serviceImpl.Transaction;

import com.example.demo.application.exceptions.TransactionExceptions.NotFound.TransactionNotFoundException;
import com.example.demo.application.exceptions.WalkerExceptions.NotFound.WalkerNotFoundException;
import com.example.demo.application.exceptions.OwnerExceptions.NotFound.OwnerNotFoundException;
import com.example.demo.application.exceptions.TransactionExceptions.NotFound.StateTransactionNotFoundException;
import com.example.demo.application.exceptions.TransactionExceptions.NotFound.TypeTransactionNotFoundException;
import com.example.demo.application.service.TransactionService.TransactionService;
import com.example.demo.domain.entity.transaction.Transaction;
import com.example.demo.domain.entity.walker.Walker;
import com.example.demo.domain.entity.owner.Owner;
import com.example.demo.domain.entity.transaction.StateTransaction;
import com.example.demo.domain.entity.transaction.TypeTransaction;
import com.example.demo.domain.repository.Walker.WalkerRepository;
import com.example.demo.domain.repository.Owner.OwnerRepository;
import com.example.demo.domain.repository.Transaction.StateTransactionRepository;
import com.example.demo.domain.repository.Transaction.TypeTransactionRepository;
import com.example.demo.domain.repository.Transaction.TransactionRepository;
import com.example.demo.infrastructure.web.projection.classBased.TransactionDTO;
import com.example.demo.infrastructure.web.projection.interfaceBased.TransactionProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final StateTransactionRepository stateTransactionRepository;
    private final TypeTransactionRepository typeTransactionRepository;
    private final OwnerRepository ownerRepository;
    private final WalkerRepository walkerRepository;

    // Servicio para registrar transacciones
    @Override
    public TransactionDTO registerTransactionService(TransactionDTO transactionDTO) {
        Walker walker = walkerRepository.findById(transactionDTO.getWalkerId())
                .orElseThrow(()-> new WalkerNotFoundException("Id del paseador no encontrado"));
        Owner owner = ownerRepository.findById(transactionDTO.getOwnerId())
                .orElseThrow(()-> new OwnerNotFoundException("Id del propietario no encontrado"));
        TypeTransaction typeTransaction = typeTransactionRepository.findById(transactionDTO.getTypeTransactionId())
                .orElseThrow(()-> new TypeTransactionNotFoundException("Id del tipo de transacción no encontrado"));
        StateTransaction stateTransaction = stateTransactionRepository.findById(transactionDTO.getStateTransactionId())
                .orElseThrow(()-> new StateTransactionNotFoundException("Id del tipo de estado de transaccion no encontrado"));

        Transaction transaction = Transaction.builder()
                .walker(walker)
                .owner(owner)
                .typeTransactionId(typeTransaction)
                .stateTransactionId(stateTransaction)
                .cost(transactionDTO.getCost())
                .build();
        // Guardando la transaccion en la base de datos usando su repositorio
        transaction = transactionRepository.save(transaction);
        //Personalizando respuesta
        Integer Id = transaction.getId();
        Integer IdOwner = transaction.getOwner().getId();
        Integer IdWalker = transaction.getWalker().getId();
        Double cost = transaction.getCost();

        return TransactionDTO.builder()
                .id(Id)
                .ownerId(IdOwner)
                .walkerId(IdWalker)
                .typeTransactionId(transactionDTO.getTypeTransactionId())
                .stateTransactionId(transactionDTO.getStateTransactionId())
                .cost(cost)
                .build();
    }

    // Servicio para editar una transaccion por su ID
    @Override
    public TransactionDTO editTransaction(Integer id, TransactionDTO transactionDTO) {
        Transaction transactionExists = transactionRepository.findById(id)
                .orElseThrow(()-> new TransactionNotFoundException("Id de la transacción no encontrada."));
        StateTransaction stateTransaction = transactionExists.getStateTransactionId();
        if (transactionDTO.getStateTransactionId() != null){
            stateTransaction = stateTransactionRepository.findById(transactionDTO.getStateTransactionId())
                    .orElseThrow(()-> new StateTransactionNotFoundException("Id del estado de transacción no ecnontrado"));
        }
        TypeTransaction typeTransaction = transactionExists.getTypeTransactionId();
        if (transactionDTO.getTypeTransactionId() != null){
            typeTransaction = typeTransactionRepository.findById(transactionDTO.getTypeTransactionId())
                    .orElseThrow(()-> new TypeTransactionNotFoundException("Id del tipo de transacción no encontrado"));
        }
        Walker walker = transactionExists.getWalker();
        if (transactionDTO.getWalkerId() != null){
            walker = walkerRepository.findById(transactionDTO.getWalkerId())
                    .orElseThrow(()-> new WalkerNotFoundException("Id del paseador no encontrado"));
        }
        Owner owner = transactionExists.getOwner();
        if (transactionDTO.getOwnerId() != null){
            owner = ownerRepository.findById(transactionDTO.getOwnerId())
                    .orElseThrow(()-> new OwnerNotFoundException("Id del propietario no ecnontrado"));
        }
        // Actualizando campos
        transactionExists.setCost(transactionDTO.getCost());
        // Guardando campos
        transactionExists = transactionRepository.save(transactionExists);

        //Personalizando respuesta
        Integer IdTransaction = transactionExists.getId();
        Integer IdOwner = transactionExists.getOwner().getId();
        Integer IdWalker = transactionExists.getWalker().getId();
        Double Cost = transactionExists.getCost();

        return TransactionDTO.builder()
                .id(IdTransaction)
                .ownerId(IdOwner)
                .walkerId(IdWalker)
                .typeTransactionId(transactionDTO.getTypeTransactionId())
                .stateTransactionId(transactionDTO.getStateTransactionId())
                .cost(Cost)
                .build();
    }

    @Override
    public List<TransactionProjection> showAllTransactionService() {
        return transactionRepository.findAllProjectedBy();
    }

    @Override
    public Optional<TransactionProjection> showTransactionByIdService(Integer id) {
        return transactionRepository.findProjectedById(id);
    }

    @Override
    public boolean deleteTransactionByIdService(Integer id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(()-> new TransactionNotFoundException("Id de la transaccion no encontrada"));
        transactionRepository.delete(transaction);
        System.out.println("Se eliminó correctamente la transacción con el ID: "+ id);
        return true;
    }
}
