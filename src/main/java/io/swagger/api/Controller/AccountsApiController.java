package io.swagger.api.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.api.Services.AccountService;
import io.swagger.model.Account;
import io.swagger.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-20T13:24:55.413Z[GMT]")
@Controller
public class AccountsApiController implements io.swagger.api.Api.AccountsApi {

    private static final Logger log = LoggerFactory.getLogger(AccountsApiController.class);

    private final ObjectMapper objectMapper;
    private UserApiController uPi;
    private final HttpServletRequest request;
    @Autowired
    private AccountService serviceAccount;

    @org.springframework.beans.factory.annotation.Autowired
    public AccountsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Account> accountGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "IBAN", required = true) Account IBAN
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Account>(objectMapper.readValue("{\n  \"acountAmount\" : 0,\n  \"typeAccount\" : \"basic\",\n  \"id\" : 1234\n}", Account.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Account>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Account>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> accountPost(@ApiParam(value = "userID",required=true) @PathVariable("userID") String userID
            ,@NotNull @ApiParam(value = "Type of the new account", required = true) @Valid @RequestParam(value = "typeAcount", required = true) Account typeAcount
    ) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteAccount() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateAccount() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }


    @RequestMapping(value= "add", method = RequestMethod.POST)
    public String processAddCheeseForm(Model model){
        model.addAttribute("accountType", io.swagger.model.accountType.values());
        return "account/add";
    }


    @GetMapping("/accountcreation")
    public String acountCreation(Model model,HttpSession session) {


        model.addAttribute("accountcreation", new Account());
        return "accountcreation";
    }

    @GetMapping("/account")
    public String newAccount(@ModelAttribute Account account, Model model,HttpSession session) {
        if(account==null)
        {
            return "null";
        }
        User user= (User) session.getAttribute("loggedin_user");
//        uPi.Navbar(model);
        Integer uId = user.getUserId();
        java.util.List<Account> accountByUser = serviceAccount.getUserAccounts(uId);
        model.addAttribute("listAccounts", accountByUser);
        return "account";
    }
    @RequestMapping( value = "/remove_iban", method = RequestMethod.GET)
    public String handleRemoveIban(Model model,@RequestParam (name="IBAN")String iban, @RequestParam(name="acountAmount") String amount ) {
        System.out.println(iban+"*************");
        System.out.println(amount+"*************");
        String error="something went wrong";
//        int am = Integer.parseInt(amount);
        serviceAccount.deleteIban(iban, amount);

        model.addAttribute("errormessage", error);
        String redirectUrl = "account";
        return "redirect:" + redirectUrl;
    }

    @PostMapping("/accountcreation")
    public String newAccountCreation(@ModelAttribute Account account, Model model,HttpSession session) {
        if(account==null)
        {
            return "null";
        }
        User user= (User) session.getAttribute("loggedin_user");
        Integer uId = user.getUserId();
        String message = "account created!!";
        String error = "no account type selected";
        String[] getValues = request.getParameterValues("selectionAccountType");
        if (getValues != null) {
            for(String valueString: getValues){
//                String keyValue[]= valueString.split(":");
                System.out.println("-------Key: " + valueString);
                serviceAccount.newAccount(account,uId,valueString);
//                System.out.println("--------Value: " + keyValue[1]);

                model.addAttribute("completeMessage", message);
            }
        }else  {
            model.addAttribute("errormessage", error);
            return "accountcreation";
        }
        String valuewords = getValues.toString();
        System.out.println("-----------------"+getValues);
//        String redirectUrl = "account";
//        return "redirect:" + redirectUrl;



        return "accountcreation";
    }


    //    @GetMapping("/accountCreation")
//    public String acountForm(Account account, Model model,HttpSession session) {
////        Account account = new Account();
////        account.typeAccount(Account.TypeAccountEnum.BASIC);
////        System.out.println(account);
//        User user= (User) session.getAttribute("loggedin_user");
//        Integer uId = user.getUserId();
//        System.out.println(uId);
//        serviceAccount.newAccount(account,uId);
////     model.addAttribute("account", account);
////        serviceAccount.newAccount(account);
//        return "account";
//    }

//    hier onder word de uid gebruikt die user doorgeeft
//    @PostMapping("/accountcreation")
//    public String newAccountCreation(@ModelAttribute Account account, Model model,HttpSession session, User user) {
//        if(account==null)
//        {
//            return "null";
//        }
//        user= (User) session.getAttribute("loggedin_user");
//        Integer uId = user.getUserId();
//        System.out.println(uId);
//        serviceAccount.newAccount(account,uId);
////        String redirectUrl = "account";
////        return "redirect:" + redirectUrl;
//        return "accountcreation";
//    }

//    @PostMapping("/account")
//    public String goToaccount(Model model) {
//
//
//        model.addAttribute("accountcreation", new Account());
//        return "accountcreation";
//    }
}
