package com.example.batchprocessing.batch;

import com.example.batchprocessing.entities.Transaction;
import com.example.batchprocessing.services.BankingService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.logging.Logger;

@Component
public class CostumItemWriter implements ItemWriter<Transaction> {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    @Autowired
    private BankingService bankingService;
    @Override
    public void write(List<? extends Transaction> list) throws Exception {
        for (Transaction t: list) {
            if(t != null){
                bankingService.commitTransaction(t);
                logger.info(t.toString());
            }
        }
    }
}
