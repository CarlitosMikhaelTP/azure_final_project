package com.example.demo.domain.repository.Transaction;

import com.example.demo.domain.entity.transaction.Transaction;
import com.example.demo.infrastructure.web.projection.interfaceBased.TransactionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query("SELECT t FROM Transaction t LEFT JOIN FETCH t.walker LEFT JOIN FETCH t.owner LEFT JOIN FETCH t.typeTransactionId LEFT JOIN FETCH t.stateTransactionId")
    List<TransactionProjection> findAllProjectedBy();

    @Query("SELECT t FROM Transaction t LEFT JOIN FETCH t.walker LEFT JOIN FETCH t.owner LEFT JOIN FETCH t.typeTransactionId LEFT JOIN FETCH t.stateTransactionId WHERE t.id = :id")
    Optional<TransactionProjection> findProjectedById(@Param("id") Integer id);
}
