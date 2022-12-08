package com.example.batchprocessing.helpers;

import com.example.batchprocessing.dto.TransactionDto;
import com.example.batchprocessing.entities.Transaction;

import java.text.ParseException;

public interface TransactionDtoToEntityAdapter {
    public Transaction map(TransactionDto transactionDto) throws ParseException;
}
