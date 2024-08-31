package com.example.demo.domain.repository.Transaction;

import com.example.demo.domain.entity.transaction.StateTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateTransactionRepository extends JpaRepository<StateTransaction, Integer> {
}
