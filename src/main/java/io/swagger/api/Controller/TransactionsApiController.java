package io.swagger.api.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordnik.swagger.annotations.ApiParam;
import io.swagger.api.Api.TransactionsApi;
import io.swagger.api.Repositories.TransactionRepository;
import io.swagger.api.Services.AccountService;
import io.swagger.api.Services.TransactionService;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.text.ParseException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-20T13:24:55.413Z[GMT]")
@Controller
public class TransactionsApiController implements TransactionsApi {

    @Autowired
    private TransactionService transService;

    @Autowired
    private AccountService accountService;

    private TransactionRepository transrepo;

    @Autowired
    private UserApiController userApiController;

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private HttpSession session;


    public ResponseEntity getTransactionById(@ApiParam(value = "Transaction ID",required=true) @PathVariable("id") Integer id) {
        return transService.getTransactionByIdResponseEntity(id);
    }
    public ResponseEntity getTransactions(@Min(1)@ApiParam(value = "The user's id", allowableValues = "") @Valid @RequestParam(value = "userid", required = false) Integer userid
            , @Min(1)@ApiParam(value = "The id of a specific transaction", allowableValues = "") @Valid @RequestParam(value = "transactionid", required = false) Integer transactionid)
    {
        return transService.getAllTransactionsResponseEntity();
    }

    public void newTransaction(@ApiParam(value = "Transaction object" ,required=true )  @Valid  Transaction body
            , @NotNull @Min(1)@ApiParam(value = "ID of user performing request", required = true, allowableValues = "") @Valid @RequestParam(value = "id", required = false) Integer id
            , @RequestBody Transaction transaction)
    {

    }

    public void postDeposit(@ApiParam(value = "The user's id" ,required=true, allowableValues="") @RequestHeader(value="userid", required=true) Integer userid
            , @ApiParam(value = "The amount to deposit" ,required=true) @RequestHeader(value="amount", required=true) Double amount
            , @RequestBody Transaction transaction)
    {

    }

    public void postWithdraw(@ApiParam(value = "The user's id" ,required=true, allowableValues="") @RequestHeader(value="userid", required=true) Integer userid
            ,@ApiParam(value = "The amount to withdraw" ,required=true) @RequestHeader(value="amount", required=true) Double amount
            , @RequestBody Transaction transaction)
    {

    }

    @Autowired
    public TransactionsApiController(ObjectMapper objectMapper, HttpServletRequest request,TransactionService transService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.transService = transService;
    }

    @RequestMapping(path = "/transaction", method = RequestMethod.GET)
    public String getTransaction(Model model,HttpSession session) {
        userApiController.Navbar(model);
        User user = (User) session.getAttribute("loggedin_user");
        model.addAttribute("accounts",accountService.getUserAccountByType("BASIC",user.getUserId()));
        model.addAttribute("transaction", new Transaction());
        return "transactionperform";
    }

    @PostMapping("/transaction")
    public String transactionSubmit(Model model,HttpSession session,@ModelAttribute Transaction transaction) throws ParseException {
        String redirectUrl = "transaction?error="+transService.newTransaction(transaction,session,"Transaction");
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/deposit")
    public String Deposit(Model model,HttpSession session) {
        userApiController.Navbar(model);
        User user = (User) session.getAttribute("loggedin_user");
        model.addAttribute("account",new Account());;
        model.addAttribute("accountsFrom",accountService.getUserAccountByType("BASIC",user.getUserId()));
        model.addAttribute("accountsTo",accountService.getUserAccountByType("SAVING",user.getUserId()));
        model.addAttribute("transaction", new Transaction());
        return "transactiondeposit";
    }

    @PostMapping("/deposit")
    public String PostDeposit(Model model, @Valid @ModelAttribute("transaction") Transaction transaction, @Valid @ModelAttribute("account") Account account,HttpSession session) throws ParseException {
        String redirectUrl = "deposit?error="+transService.newTransaction(transaction,session,"Deposit");
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/withdraw")
    public String Log(Model model,HttpSession session) {
        userApiController.Navbar(model);
        User user = (User) session.getAttribute("loggedin_user");
        model.addAttribute("accountsFrom",accountService.getUserAccountByType("SAVING",user.getUserId()));
        model.addAttribute("accountsTo",accountService.getUserAccountByType("BASIC",user.getUserId()));
        model.addAttribute("account",new Account());
        model.addAttribute("transaction", new Transaction());
        return "transactionwithdraw";
    }

    @PostMapping("/withdraw")
    public String PostWithdraw(Model model, @ModelAttribute Transaction transaction, @ModelAttribute Account account,HttpSession session) throws ParseException {
        String redirectUrl = "withdraw?error="+transService.newTransaction(transaction,session,"Withdraw");
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/log")
    public String Withdraw(Model model,HttpSession session) {
        userApiController.Navbar(model);
        User user = (User) session.getAttribute("loggedin_user");
        model.addAttribute("listall", transService.getTransactionsById(user.getUserId()));
        return "transactionlog";
    }

    @GetMapping("/transhome")
    public String Home(HttpSession session,Model model) {
        userApiController.Navbar(model);
        User user= (User) session.getAttribute("loggedin_user");
        model.addAttribute("username",user.getName());
        return "transactionhome";
    }
}
