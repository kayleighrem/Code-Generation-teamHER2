package io.swagger.api.Services;

import io.swagger.api.Repositories.TransactionRepository;
import io.swagger.model.Transaction;
import io.swagger.model.User;
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

    public String newTransaction(Transaction transaction, User user)
    {
        transaction.setTransactionDate(new Date());
        transaction.setUserPerforming(1);
        transaction.setTo("okl");
        transaction.setFrom("ok");
        transaction.status(Transaction.StatusEnum.COMPLETE);
        transaction.setId(2);
        transRepo.save(transaction);
//        transaction.setUserPerforming(user.getUserId());
//
//        String IBAN = transaction.getTo();
//        if(Modulo97.verifyCheckDigits(IBAN))
//        {
//            if(transaction.getAmount() > 1000f)
//            {
//                return "Transaction amount is too high";
//            }
//            //else if account amout lager wordt dan getal
//            //else if gebruiker voert zoveelste transaction uit
//            else {
//                Date date = new Date();
//                transaction.setTransactionDate(date);
//
//                transaction.status(Transaction.StatusEnum.COMPLETE);
//                return "Transaction Success!";
//            }
//        }
//        else
//        {
//            transaction.status(Transaction.StatusEnum.ERROR);
//            return "Invalid IBAN";
//        }
    return "ok";
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
