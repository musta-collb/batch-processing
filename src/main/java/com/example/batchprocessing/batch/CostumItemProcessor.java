package com.example.batchprocessing.batch;

import com.example.batchprocessing.dto.TransactionDto;
import com.example.batchprocessing.entities.Transaction;
import com.example.batchprocessing.helpers.TransactionDtoToEntityAdapter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CostumItemProcessor implements ItemProcessor<TransactionDto, Transaction> {
    /**
     * @param transactionDto
     * @return
     * @throws Exception
     */
    @Autowired
    private TransactionDtoToEntityAdapter toEntityAdapter;
    @Override
    public Transaction process(TransactionDto transactionDto) throws Exception {
        try {
            return toEntityAdapter.map(transactionDto);
        }catch (Exception e){
            return null;
        }
    }
}
