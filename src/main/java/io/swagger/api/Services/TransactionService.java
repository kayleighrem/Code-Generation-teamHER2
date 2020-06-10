package io.swagger.api.Services;

import io.swagger.api.Repositories.TransactionRepository;
import io.swagger.model.Transaction;
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
       // String toIBAN = transaction.getTo();
      //  IBANCheck check = new IBANCheck();
     //   Boolean perform = true;



            Date date = new Date();
            transaction.setTransactionDate(null);
            transRepo.save(transaction);
            return "Transaction Success!";



    }

    public void depositTransaction(Transaction transaction)
    {
        //
    }

    public void withdrawTransaction(Transaction transaction)
    {
        //
    }

    public void test()
    {
        System.out.println("echo");
    }


}
