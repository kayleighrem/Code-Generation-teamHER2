package io.swagger.api.Services;

import io.swagger.api.Repositories.AccountRepository;
import io.swagger.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

//makes a iban string
    public static String generatedIban(int upperRange){
        Random random = new Random();
        int test = random.nextInt(999999999);
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
        account.setId(3);
        account.setIBAN("IBAN4040TESTLOL");
        account.setTypeAccount(Account.TypeAccountEnum.BASIC);
        account.setAcountAmount(100);
        accountRepository.save(account);


        return "Saved";
    }




}
