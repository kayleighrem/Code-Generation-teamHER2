package io.swagger.api.Services;

import io.swagger.api.Repositories.TransactionRepository;
import io.swagger.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService
{

    @Autowired
    private TransactionRepository transRepo;

    public TransactionService(TransactionRepository transRepo)
    {
       this.transRepo = transRepo;
    }

    public List<Transaction> getTransactions()
    {
       return (List<Transaction>) transRepo.findAll();
    }

    public Transaction getTransactionById(Long id)
    {
        return transRepo.findOne(id);
    }

    public void newTransaction(Transaction transaction)
    {
        transRepo.save(transaction);
    }

    public void depositTransaction(Transaction transaction)
    {
        //
    }

    public void withdrawTransaction(Transaction transaction)
    {
        //
    }


}
