package com.example.batchprocessing.repositories;

import com.example.batchprocessing.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}