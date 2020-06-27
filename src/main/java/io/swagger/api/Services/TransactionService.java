package io.swagger.api.Services;

import io.swagger.api.Repositories.TransactionRepository;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import nl.garvelink.iban.Modulo97;
import org.apache.commons.lang3.time.DateUtils;
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

    //Sets the service layer for transaction
    public TransactionService(TransactionRepository transRepo)
    {
       this.transRepo = transRepo;
    }

    //Retrieves all transactions in the database
    public List<Transaction> getTransactions()
    {
       return (List<Transaction>) transRepo.findAll();
    }

    //Retrieves all transactions by user id
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

    //Retrieves the amount of transactions a user has performed today
    public Integer getTodaysUserTransactions(Integer id)  {
        Date today = new Date();
        Integer dailyTransactions = 0;

        for(Transaction transaction : getTransactionsById(id))
        {
            if(DateUtils.isSameDay(today,transaction.getTransactionDate()))
            {
                dailyTransactions++;
            }
        }
        return dailyTransactions;
    }

    //Setting the first transactions variables and controlling what type of transaction the current transaction is
    public String newTransaction(Transaction transaction, HttpSession session,String type)  {
        User performingUser = (User) session.getAttribute("loggedin_user");
        transaction.setUserPerforming(performingUser.getUserId());
        transaction.status(Transaction.StatusEnum.ERROR);
        Account accountFrom = accountService.getUserAccountByIBAN(transaction.getFrom());
        Account accountTo = accountService.getUserAccountByIBAN(transaction.getTo());

        switch(type)
        {
            case"Deposit":
            case"Withdraw":
                return secondaryTransactionConditions(transaction,accountFrom,accountTo,type);
            case"Transaction":
                return transactionConditions(transaction,accountFrom,accountTo);
        }
        return "Unknown Error";
    }

    //Checks if the current transaction can be performed. Type = Deposit/Withdraw
    public String secondaryTransactionConditions(Transaction transaction,Account accountFrom,Account accountTo, String type)
    {
        if(accountFrom.getAcountAmount() >= 0 && accountFrom.getAcountAmount() - transaction.getMoney() >= 0)
        {
            transaction.setDescription(type+": "+transaction.getDescription());
            performTransaction(accountFrom,accountTo,transaction);
            return "Transaction Complete";
        }
            return "Insufficient funds";
    }

    //Checks if the current transaction can be performed. Type = Normal Transaction
    private String transactionConditions(Transaction transaction, Account account, Account accountTo)  {
        if(Modulo97.verifyCheckDigits(transaction.getTo()))
        {
            if(account.getTypeAccount().equals(Account.TypeAccountEnum.BASIC))
            {
                if (transaction.getMoney() < 1000)
                {
                    if (account.getAcountAmount() - transaction.getMoney() > 0)
                    {
                        if (getTodaysUserTransactions(transaction.getUserPerforming()) < 5)
                        {
                            performTransaction(account, accountTo, transaction);
                            return "Transaction Success!";
                        }
                        return "Over daily transaction limit. Please try again Tomorrow.";
                    }
                    return "Transaction will put account in red";
                }
                return "Transaction amount is too high";
            }
            return "Cannot transfer to another user's savings account";
        }
        else
        {
            return "Invalid IBAN";
        }
    }

    //If the transactionchecks are cleared, the amount of money will be moved from accounts and the transaction will
    //be saved to the database.
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
