package com.example.batchprocessing.services;

import com.example.batchprocessing.entities.Compte;
import com.example.batchprocessing.entities.Transaction;
import com.example.batchprocessing.repositories.CompteRepository;
import com.example.batchprocessing.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankingServiceMySqlImp implements BankingService {
    @Autowired
    public CompteRepository compteRepository;
    @Autowired
    public TransactionRepository transactionRepository;

    @Override
    public Compte getCompteById(long id) {
        return compteRepository.getReferenceById(id);
    }

    @Override
    public Transaction commitTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getTransactionById(long id) {
        return transactionRepository.getReferenceById(id);
    }
}
