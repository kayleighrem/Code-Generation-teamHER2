package io.swagger.api.Services;

import io.swagger.api.Repositories.AccountRepository;
import io.swagger.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAccount()
    {
        return (List<Account>) accountRepository.findAll();
    }

    //makes a iban string with a random 10 long number
    //makes a iban with 2 random numbers
    public static String generatedIban(){
         int maxRangeIban =999999999;
        int minRangeIban =100000000;
        Random lastTen = new Random();
        Random firstTwo = new Random();
        int lTen = lastTen.nextInt((maxRangeIban - minRangeIban)+1)+ minRangeIban;
        int fTWo = lastTen.nextInt((99 - 0)+1)+ 0;
        String iban= "NL"+fTWo+"INHO0"+lTen;
        return iban;
    }

    public String newAccount(Account account, Integer user,String type)
    {
        account.setId(user);
        String iban = generatedIban();
        account.setIBAN(iban);
        if(type.equals("basic")  ) {
            account.setTypeAccount(Account.TypeAccountEnum.BASIC);
        }else if (type.equals("saving")){
            account.setTypeAccount(Account.TypeAccountEnum.SAVING);
        }
        account.setAcountAmount(0.00);
        accountRepository.save(account);
        return "Saved";
    }

    public boolean CheckIfAccountExists(String string) {
        return false;
    }

    public List<Account> getUserAccountByType(String basic, Integer uid)
    {
        List<Account> accounts = new ArrayList<>();

        for(Account account : getUserAccounts(uid))
        {
            if(account.getTypeAccount().equals(Account.TypeAccountEnum.valueOf(basic)))
            {
                accounts.add(account);
            }
        }
        return accounts;
    }

    public Account getUserAccountByIBAN(String Iban)
    {
        for(Account account : getAccount())
        {
            if(account.getIBAN().equals(Iban))
            {
                return account;
            }
        }
        return null;
    }

    public String deleteIban(String IbanID, String amount) {
        Account ac = getUserAccountByIBAN(IbanID);
       if(amount.equals("0.0")) {
           accountRepository.delete(ac);
           return "IBAN has been deleted";
       }
            return  "you still got money on this iban";
    }

    public java.util.List<Account> getUserAccounts(Integer id)
    {
        java.util.List<Account> userAccounts = new ArrayList<>();
        for(Account account : getAccount())
        {
            if(account.getId().equals(id))
            {
                userAccounts.add(account);
            }
        }
        return userAccounts;
    }
}
