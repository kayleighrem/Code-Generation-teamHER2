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
static int maxRangeIban =999999999;
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAccount()
    {
        return (List<Account>) accountRepository.findAll();
    }

//makes a iban string
    public static String generatedIban(int upperRange){
        Random random = new Random();
        int test = random.nextInt(maxRangeIban);
        String iban= "Nl20TEST"+test;
        System.out.println(iban);
        System.out.println("hoi");
        return iban;
    }

    public void  GetAllEnums(){
        System.out.println("hoi");
//        List<Account.TypeAccountEnum> TypeList =new ArrayList<>(EnumSet.allOf(Account.TypeAccountEnum.class));
//        System.out.println(TypeList);
//        return TypeList;
    }

    public String newAccount(Account account)
    {
        account.setId(4);
        String iban = generatedIban(maxRangeIban);
        account.setIBAN(iban);
        account.setTypeAccount(Account.TypeAccountEnum.SAVING);
        account.setAcountAmount(100);
        accountRepository.save(account);
        return "Saved";
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
