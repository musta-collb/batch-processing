package com.example.batchprocessing.helpers;

import com.example.batchprocessing.services.BankingService;
import com.example.batchprocessing.dto.TransactionDto;
import com.example.batchprocessing.entities.Compte;
import com.example.batchprocessing.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TransactionAdapter implements TransactionDtoToEntityAdapter {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy hh:mm");
    @Autowired
    private BankingService bankingService;
    @Override
    public Transaction map(TransactionDto transactionDto) throws ParseException {
        Compte compte = bankingService.getCompteById(Long.parseLong(transactionDto.getCompteId()));
        Transaction transaction = new Transaction();
        transaction.setIdTransaction(Long.parseLong(transactionDto.getIdTransaction()));
        transaction.setDateTransaction(dateFormat.parse(transactionDto.getDateTransaction()));
        transaction.setMontant(Double.parseDouble(transactionDto.getMontant()));
        transaction.setDateDebit(new Date());
        transaction.setCompte(compte);
        return transaction;
    }
}
