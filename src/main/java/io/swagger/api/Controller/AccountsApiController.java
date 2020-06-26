package io.swagger.api.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.api.Services.AccountService;
import io.swagger.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-20T13:24:55.413Z[GMT]")
@Controller
public class AccountsApiController implements io.swagger.api.Api.AccountsApi {

    private static final Logger log = LoggerFactory.getLogger(AccountsApiController.class);

    private final ObjectMapper objectMapper;

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


    @GetMapping("/account")
    public String acountForm(Model model) {
//        Account account = new Account();
//        account.typeAccount(Account.TypeAccountEnum.BASIC);
//        System.out.println(account);
      serviceAccount.newAccount(new Account());
//     model.addAttribute("account", account);
//        serviceAccount.newAccount(account);
        return "account";
    }
    @GetMapping("/accountcreation")
    public String acountCreation(Model model) {
        model.addAttribute("accountcreation", new Account());
        return "accountCreation";
    }

    @PostMapping("/accountcreation")
    public String newAccount(@ModelAttribute Account account, Model model) {
        if(account==null)
        {
            return "null";
        }
        serviceAccount.newAccount(account);
        return "accountCreation";
    }
}
