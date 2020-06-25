package io.swagger.api.Services;

import io.swagger.api.Repositories.TransactionRepository;
import io.swagger.model.Transaction;
import nl.garvelink.iban.Modulo97;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public String newTransaction(Transaction transaction)
    {
        transaction.setUserPerforming(2);
        transaction.status(Transaction.StatusEnum.ERROR);
        String IBAN = transaction.getTo();
        if(Modulo97.verifyCheckDigits(IBAN))
        {
            Date date = new Date();
            transaction.setTransactionDate(null);
            transRepo.save(transaction);
            return "Transaction Success!";
        }
        else
        {
            return "Invalid IBAN";
        }

    }

    public String depositTransaction(Transaction transaction)
    {
        return "Deposit Error Message";
    }

    public String withdrawTransaction(Transaction transaction)
    {
        return "Withdraw Error Message";
    }
}
