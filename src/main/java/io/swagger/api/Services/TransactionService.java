package io.swagger.api.Services;

import io.swagger.api.Repositories.TransactionRepository;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import nl.garvelink.iban.Modulo97;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    public List<Transaction> getTransactionsById(Integer id)
    {
        List<Transaction> userTransactions = new ArrayList<>();
        for(Transaction transaction : getTransactions())
        {
            if(transaction.getUserPerforming() == id)
            {
                userTransactions.add(transaction);
            }
        }
        return userTransactions;
    }

    public String newTransaction(Transaction transaction, HttpSession session, Account account)
    {
        User performingUser = (User) session.getAttribute("loggedin_user");
        transaction.setUserPerforming(performingUser.getUserId());
        transaction.setFrom(performingUser.getName());
        transaction.status(Transaction.StatusEnum.PENDING);
        transaction.setAmount(100.00d);

        System.out.println(transaction);

        String IBAN =transaction.getTo();
        if(Modulo97.verifyCheckDigits(IBAN))
        {
            if(transaction.getAmount() < 1000d)
            {
                if(account.getAcountAmount()-transaction.getAmount() > 0)
                {
                    //else if gebruiker voert zoveelste transaction uit
                    Date date = new Date();
                    transaction.setTransactionDate(date);
                    transaction.status(Transaction.StatusEnum.COMPLETE);
                    transRepo.save(transaction);
                    return "Transaction Success!";
                }
                return "Transaction will put account in red";
            }
            return "Transaction amount is too high";
        }
        else
        {
            transaction.status(Transaction.StatusEnum.ERROR);
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
