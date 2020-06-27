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

    @Autowired
    private AccountService accountService;

    public TransactionService(TransactionRepository transRepo,HttpSession session)
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

    public String newTransaction(Transaction transaction, HttpSession session,String type)
    {
        User performingUser = (User) session.getAttribute("loggedin_user");
        transaction.setUserPerforming(performingUser.getUserId());
        transaction.status(Transaction.StatusEnum.ERROR);
        Account accountFrom = accountService.getUserAccountByIBAN(transaction.getFrom());
        Account accountTo = accountService.getUserAccountByIBAN(transaction.getTo());

        switch(type)
        {
            case "Secondary":
                return secondaryTransactionConditions(transaction,accountFrom,accountTo,type);
            case"Primary":
                return transactionConditions(transaction,accountFrom,accountTo);
        }
        return "Unknown Error";
    }

    public String secondaryTransactionConditions(Transaction transaction,Account accountFrom,Account accountTo, String type)
    {
        if(accountFrom.getAcountAmount() > 0 && accountFrom.getAcountAmount() - transaction.getMoney() > 0)
        {
            transaction.setDescription(type+": "+transaction.getDescription());
            performTransaction(accountFrom,accountTo,transaction);
            return "Success";
        }
        return "Error";
    }

    private String transactionConditions(Transaction transaction, Account account, Account accountTo)
    {
        if(Modulo97.verifyCheckDigits(transaction.getTo()))
        {
            if(transaction.getMoney() < 1000)
            {
                if(account.getAcountAmount() - transaction.getMoney() > 0)
                {
                    //else if gebruiker voert zoveelste transaction uit
                    performTransaction(account,accountTo,transaction);
                    return "Transaction Success!";
                }
                return "Transaction will put account in red";
            }
            return "Transaction amount is too high";
        }
        else
        {
            return "Invalid IBAN";
        }
    }

    private void performTransaction(Account accountFrom, Account accountTo,Transaction transaction)
    {
        Date date = new Date();
        transaction.setTransactionDate(date);

        Double transactionAmount = transaction.getMoney();
        accountTo.setAcountAmount(accountTo.getAcountAmount()+transactionAmount);
        accountFrom.setAcountAmount(accountFrom.getAcountAmount() - transactionAmount);
        transaction.setStatus(Transaction.StatusEnum.COMPLETE);
        transRepo.save(transaction);
    }

}
