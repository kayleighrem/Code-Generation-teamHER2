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

    public String newTransaction(Transaction transaction, HttpSession session, Account account)
    {
        User performingUser = (User) session.getAttribute("loggedin_user");
        transaction.setUserPerforming(performingUser.getUserId());
        transaction.setFrom(performingUser.getName());
        transaction.status(Transaction.StatusEnum.PENDING);
        transaction.setAmount(transaction.getAmount());
        System.out.println("Money: "+transaction.getMoney());

        Integer amount = Integer.parseInt(transaction.getAmount());

        if(accountService.CheckIfAccountExists(transaction.getTo()))
        {
            return "Recipient's IBAN not found.";
        }

        String IBAN = transaction.getTo();
        if(Modulo97.verifyCheckDigits(IBAN.toString()))
        {
            if(amount < 1000) {
//                if(account.getAcountAmount() - transaction.getAmount() > 0)
//                {
                //else if gebruiker voert zoveelste transaction uit
                Date date = new Date();
                transaction.setTransactionDate(date);
                transaction.status(Transaction.StatusEnum.COMPLETE);
                transRepo.save(transaction);
                return "Transaction Success!";
//            }
//                return "Transaction will put account in red";
            }
            return "Transaction amount is too high";
        }
        else
        {
            transaction.status(Transaction.StatusEnum.ERROR);
            return "Invalid IBAN";
        }
    }

    public String depositTransaction(Transaction transaction,Account account,HttpSession session)
    {
//        if(transaction.getAmount() > 0)
//        {
//            User performingUser = (User) session.getAttribute("loggedin_user");
//            transaction.setUserPerforming(performingUser.getUserId());
//            transaction.setDescription("Deposit");
//            transaction.setUserPerforming(performingUser.getUserId());
//            transaction.setFrom(performingUser.getName());
//            transaction.setTransactionDate(new Date());
//            transaction.setTo("STATIC IBAN");
//            transaction.setStatus(Transaction.StatusEnum.COMPLETE);
//            transRepo.save(transaction);
//            return "Success";
//        }
        return "Deposit Error Message";
    }

    public String withdrawTransaction(Transaction transaction,Account account,HttpSession session)
    {
//        if(transaction.getAmount() > 0)
//        {
//            User performingUser = (User) session.getAttribute("loggedin_user");
//            transaction.setUserPerforming(performingUser.getUserId());
//            transaction.setDescription("Withdraw");
//            transaction.setUserPerforming(performingUser.getUserId());
//            transaction.setFrom(performingUser.getName());
//            transaction.setTransactionDate(new Date());
//            transaction.setTo("STATIC IBAN");
//            transaction.setStatus(Transaction.StatusEnum.COMPLETE);
//            transRepo.save(transaction);
//            return "Success";
//        }
        return "Success";
    }

    private boolean transactionConditions(Transaction transaction, Account account, User user)
    {
        return false;
    }

}
