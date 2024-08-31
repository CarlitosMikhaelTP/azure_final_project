package com.example.demo.domain.repository.Transaction;

import com.example.demo.domain.entity.transaction.TypeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeTransactionRepository extends JpaRepository<TypeTransaction, Integer> {
}
