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

    //makes a iban string
    public static String generatedIban(){
         int maxRangeIban =999999999;
        int minRangeIban =10000000;
        Random random = new Random();
        int test = random.nextInt((maxRangeIban - minRangeIban)+1)+ minRangeIban;
        String iban= "Nl20TEST"+test;
        return iban;
    }

//    public void  GetAllEnums(){
//        System.out.println("hoi");
////        List<Account.TypeAccountEnum> TypeList =new ArrayList<>(EnumSet.allOf(Account.TypeAccountEnum.class));
////        System.out.println(TypeList);
////        return TypeList;
//    }

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



//   basicly not needed anymore
//    public String newAccountbasic(Account account, Integer user)
//    {
//        account.setId(user);
//        System.out.println("--------------");
//        System.out.println(user);
//        System.out.println("--------------");
//        String iban = generatedIban(maxRangeIban);
//        account.setIBAN(iban);
//        account.setTypeAccount(Account.TypeAccountEnum.BASIC);
//        account.setAcountAmount(100);
//        accountRepository.save(account);
//        return "Saved";
//    }

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
//        System.out.println("---------testing------------"+accounts);
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
    public void deleteIban(String IbanID, String amount) {
        System.out.println(amount+"------");
        Account ac = getUserAccountByIBAN(IbanID);
        System.out.println(ac+"------");
       if(amount.equals("0.0")){
           accountRepository.delete(ac);
           System.out.println("int werkt");
       }else{
           System.out.println("werkt niet");

       }
        System.out.println(amount);
//    if(amount!= 0.00) {

    }
//    }


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
//        System.out.println(userAccounts);
        return userAccounts;
    }

}
