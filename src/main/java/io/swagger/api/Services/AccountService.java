package io.swagger.api.Services;

import io.swagger.api.Repositories.AccountRepository;
import io.swagger.model.Account;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAccount()
    {
        return (List<Account>) accountRepository.findAll();
    }


    public void  GetAllEnums(){
        System.out.println("hoi");
//        List<Account.TypeAccountEnum> TypeList =new ArrayList<>(EnumSet.allOf(Account.TypeAccountEnum.class));
//        System.out.println(TypeList);
//        return TypeList;
    }


}
