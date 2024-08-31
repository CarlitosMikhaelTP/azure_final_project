package com.example.demo.application.service.TransactionService;

import com.example.demo.application.exceptions.TransactionExceptions.Insufficient.InsufficientMoneyException;
import com.example.demo.application.exceptions.TransactionExceptions.NotFound.StateTransactionNotFoundException;
import com.example.demo.application.exceptions.TransactionExceptions.NotFound.TypeTransactionNotFoundException;
import com.example.demo.domain.entity.transaction.Transaction;
import com.example.demo.domain.entity.walker.Walker;
import com.example.demo.domain.entity.walk.Walk;
import com.example.demo.domain.entity.owner.Owner;
import com.example.demo.domain.entity.transaction.StateTransaction;
import com.example.demo.domain.entity.transaction.TypeTransaction;
import com.example.demo.domain.repository.Walker.WalkerRepository;
import com.example.demo.domain.repository.Walk.WalkRepository;
import com.example.demo.domain.repository.Owner.OwnerRepository;
import com.example.demo.domain.repository.Transaction.StateTransactionRepository;
import com.example.demo.domain.repository.Transaction.TypeTransactionRepository;
import com.example.demo.domain.repository.Transaction.TransactionRepository;
import com.example.demo.infrastructure.web.projection.classBased.PayRequestDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PayService {

    @Autowired
    private WalkerRepository walkerRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private WalkRepository walkRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private StateTransactionRepository stateTransactionRepository;

    @Autowired
    private TypeTransactionRepository typeTransactionRepository;

    public void processPay(PayRequestDTO pagoRequest) {
        Optional<Walker> optionalWalker = walkerRepository.findById(pagoRequest.getIdWalker());
        Optional<Owner> optionalOwner = ownerRepository.findById(pagoRequest.getIdOwner());
        Optional<Walk> optionalWalk = walkRepository.findById(pagoRequest.getIdPaseo());

        if (optionalWalker.isPresent() && optionalOwner.isPresent() && optionalWalk.isPresent()) {
            Walker walker = optionalWalker.get();
            Owner owner = optionalOwner.get();
            Walk walk = optionalWalk.get();

            Double costWalk = walk.getCost();

            // Verificar si el propietario tiene saldo suficiente
            Double moneyOwner = owner.getMoney();
            if (moneyOwner.compareTo(costWalk) < 0) {
                throw new InsufficientMoneyException("There's not enough money");
            }

            // Realizar el pago: restar saldo al propietario y sumar al paseador
            Double newMoneyOwner = moneyOwner - costWalk;
            Double newMoneyWalker = walker.getMoney() + costWalk;

            // Actualizar los saldos
            owner.setMoney(newMoneyOwner);
            walker.setMoney(newMoneyWalker);

            // Crear una nueva transacción
            Transaction transaction = new Transaction();
            transaction.setWalker(walker);
            transaction.setOwner(owner);
            transaction.setCost(costWalk);

            // Asignar las claves foráneas (FK)
            StateTransaction stateTransaction = stateTransactionRepository.findById(pagoRequest.getIdEstadoTransaccion())
                    .orElseThrow(() -> new StateTransactionNotFoundException("Estado de transacción no encontrado"));
            transaction.setStateTransactionId(stateTransaction);

            TypeTransaction typeTransaction = typeTransactionRepository.findById(pagoRequest.getIdTipoTransaccion())
                    .orElseThrow(() -> new TypeTransactionNotFoundException("Tipo de transacción no encontrado"));
            transaction.setTypeTransactionId(typeTransaction);

            // Guardar la transacción y actualizar los saldos en la base de datos
            transactionRepository.save(transaction);
            ownerRepository.save(owner);
            walkerRepository.save(walker);

            // Resto de tu lógica para procesar el pago
            // ...
        } else {
            // Manejo si no se encuentran los elementos
            throw new EntityNotFoundException("Paseador, Propietario o Paseo no encontrados");
        }
    }
}
