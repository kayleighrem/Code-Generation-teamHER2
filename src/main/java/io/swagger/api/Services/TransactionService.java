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

    public String newTransaction(Transaction transaction, HttpSession session)
    {
        User performingUser = (User) session.getAttribute("loggedin_user");
        transaction.setUserPerforming(performingUser.getUserId());
        transaction.setFrom(performingUser.getName());
        transaction.status(Transaction.StatusEnum.PENDING);

        String IBAN =transaction.getTo();
        if(Modulo97.verifyCheckDigits(IBAN))
        {
            if(transaction.getAmount() > 1000f)
            {
                return "Transaction amount is too high";
            }
            //else if account amout lager wordt dan getal
            //else if gebruiker voert zoveelste transaction uit
            else {
                Date date = new Date();
                transaction.setTransactionDate(date);
                transaction.status(Transaction.StatusEnum.COMPLETE);
                transRepo.save(transaction);
                return "Transaction Success!";
            }
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

    public String test(Account account)
    {
        System.out.println(account);
        return "account";
    }
}
