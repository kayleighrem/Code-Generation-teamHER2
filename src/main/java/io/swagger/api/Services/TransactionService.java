package io.swagger.api.Services;

import io.swagger.api.Repositories.TransactionRepository;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import nl.garvelink.iban.Modulo97;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<Transaction> getTransactionsById(Integer id) {
        List<Transaction> userTransactions = new ArrayList<>();
        for(Transaction transaction : getTransactions()) {
            if(transaction.getUserPerforming() == id) {
                userTransactions.add(transaction);
            }
        }
        return userTransactions;
    }

    //Retrieves the amount of transactions a user has performed today
    public Integer getTodaysUserTransactions(Integer id)  {
        Date today = new Date();
        Integer dailyTransactions = 0;

        for(Transaction transaction : getTransactionsById(id)) {
            if(DateUtils.isSameDay(today,transaction.getTransactionDate())) {
                dailyTransactions++;
            }
        }
        return dailyTransactions;
    }

    //Setting the first transactions variables and controlling what type of transaction the current transaction is
    public String newTransaction(Transaction transaction, HttpSession session,String type) throws NullPointerException  {
        User performingUser = (User) session.getAttribute("loggedin_user");
        transaction.setUserPerforming(performingUser.getUserId());
        transaction.status(Transaction.StatusEnum.ERROR);
        Account accountFrom = accountService.getUserAccountByIBAN(transaction.getFrom());
        Account accountTo = accountService.getUserAccountByIBAN(transaction.getTo());

        if(accountFrom==null)
        {
            return "No user account found, please create a new basic account.";
        }

        switch(type) {
            case"Transaction":
                return primaryTransactionConditions(transaction,accountFrom,accountTo);
            case"Deposit":
            case"Withdraw":
                return secondaryTransactionConditions(transaction,accountFrom,accountTo,type);
        }
        return "Unknown Error";
    }

    //Checks if the current transaction can be performed. Type = Deposit/Withdraw
    //Performs check on recipients account current balance and balance minus the transaction amount
    public String secondaryTransactionConditions(Transaction transaction,Account accountFrom,Account accountTo, String type) {
        if(accountFrom.getAcountAmount() >= 0 && accountFrom.getAcountAmount() - transaction.getMoney() >= 0) {
            transaction.setDescription(type+": "+transaction.getDescription());
            performTransaction(accountFrom,accountTo,transaction);
            return "Transaction Complete";
        }
            return "Insufficient funds";
    }

    //Checks if the current transaction can be performed. Type = Normal Transaction
    //Performs IBAN format check, recipients account type, transaction amount, recipients balance minus transaction amount,
    // and the amount of daily transactions a user has perfomed.
    private String primaryTransactionConditions(Transaction transaction, Account account, Account accountTo)  {
        if(Modulo97.verifyCheckDigits(transaction.getTo()))
        { if(account.getTypeAccount().equals(Account.TypeAccountEnum.BASIC)) {
                if (transaction.getMoney() < 1000) {
                    if (account.getAcountAmount() - transaction.getMoney() > 0) {
                        if (getTodaysUserTransactions(transaction.getUserPerforming()) < 5) {
                            performTransaction(account, accountTo, transaction);
                            return "Transaction Success!";
                        }return "Over daily transaction limit. Please try again Tomorrow.";
                    }return "Transaction will put account in red";
                }return "Transaction amount is too high";
            }return "Cannot transfer to another user's savings account";
        }
        else {
            return "Invalid IBAN";
        }
    }

    //If the transactionchecks are cleared, the transaction money will be transferred between accounts and the transaction will
    //be stored to the database.
    //Some final transaction properties will also be set.
    private ResponseEntity performTransaction(Account accountFrom, Account accountTo,Transaction transaction)
    {
        Date date = new Date();
        transaction.setTransactionDate(date);

        Double transactionAmount = transaction.getMoney();
        accountTo.setAcountAmount(accountTo.getAcountAmount()+transactionAmount);
        accountFrom.setAcountAmount(accountFrom.getAcountAmount() - transactionAmount);

        transaction.setStatus(Transaction.StatusEnum.COMPLETE);
        transRepo.save(transaction);

        return TransactionResponseEntity(transaction);
    }

    //Returns all transactions in the database as a response entity
    public ResponseEntity getAllTransactionsResponseEntity() {
        List<Transaction> transactions = getTransactions();
        return ResponseEntity
                .status(200)
                .body(transactions);
    }

    //Returns all transactions from a user in the database as a response entity
    public ResponseEntity getTransactionByIdResponseEntity(Integer id) {
        List<Transaction> transactions = getTransactionsById(id);
        return ResponseEntity
                .status(200)
                .body(transactions);
    }

    //Returns the current transaction as a response entity
    public ResponseEntity TransactionResponseEntity(Transaction body){
      return ResponseEntity
              .status(200)
              .body(body);
    }
}


