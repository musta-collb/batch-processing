package com.example.batchprocessing.services;

import com.example.batchprocessing.entities.Compte;
import com.example.batchprocessing.entities.Transaction;

import java.util.List;

public interface BankingService {
    public Compte getCompteById(long id);
    public Transaction commitTransaction(Transaction transaction);
    public List<Transaction> findAllTransactions();
    public Transaction getTransactionById(long id);
}
