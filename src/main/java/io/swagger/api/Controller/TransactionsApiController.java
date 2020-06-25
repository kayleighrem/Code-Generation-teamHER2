package io.swagger.api.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.api.Api.TransactionsApi;
import io.swagger.api.Repositories.TransactionRepository;
import io.swagger.api.Services.TransactionService;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;





@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-20T13:24:55.413Z[GMT]")
@Controller
public class TransactionsApiController implements TransactionsApi {

    private TransactionService transService;

    private TransactionRepository transrepo;

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionsApiController(ObjectMapper objectMapper, HttpServletRequest request,TransactionService transService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.transService =transService;
    }

    /*
       Retrieves a list of a user's transaction log
     */
    public List<Transaction> getTransactions(@Min(1)@ApiParam(value = "The user's id", allowableValues = "") @Valid @RequestParam(value = "userid", required = false) Integer userid
, @Min(1)@ApiParam(value = "The id of a specific transaction", allowableValues = "") @Valid @RequestParam(value = "transactionid", required = false) Integer transactionid
) {
        return transService.getTransactions();
    }


    /*
       Performs a new transaction
     */
    public void newTransaction(@ApiParam(value = "Transaction object" ,required=true )  @Valid  Transaction body
, @NotNull @Min(1)@ApiParam(value = "ID of user performing request", required = true, allowableValues = "") @Valid @RequestParam(value = "id", required = false) Integer id
, @RequestBody Transaction transaction)
    {
        transService.newTransaction(transaction,new User());
    }

    /*
    Performs a new deposit transaction
    */
    public void postDeposit(@ApiParam(value = "The user's id" ,required=true, allowableValues="") @RequestHeader(value="userid", required=true) Integer userid
, @ApiParam(value = "The amount to deposit" ,required=true) @RequestHeader(value="amount", required=true) Double amount
            , @RequestBody Transaction transaction)
    {
        transService.depositTransaction(transaction);
    }

    /*
    Performs a new withdrawal transaction
    */
    public void postWithdraw(@ApiParam(value = "The user's id" ,required=true, allowableValues="") @RequestHeader(value="userid", required=true) Integer userid
,@ApiParam(value = "The amount to withdraw" ,required=true) @RequestHeader(value="amount", required=true) Double amount
            , @RequestBody Transaction transaction)
    {
        transService.getTransactions();
    }

    @RequestMapping(path = "/transaction", method = RequestMethod.GET)
    public String greetingForm(Model model,HttpSession session) {
        System.out.println(session.getAttribute("transaction"));
        model.addAttribute("transaction", new Transaction());
        return "transactionperform";
    }

    @PostMapping("/transaction")
    public String transactionSubmit(@ModelAttribute Transaction transaction, HttpSession session,Model model) {
        String result = transService.newTransaction(transaction,new User());
        System.out.println(transaction);
        model.addAttribute("errormessage",result);
        model.addAttribute("transaction", new Transaction());
        return "transactionperform";
    }

    @GetMapping("/deposit")
    public String Deposit(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "transactiondeposit";
    }

    @PostMapping("/deposit")
    public String PostDeposit(Model model, @ModelAttribute Transaction transaction) {
        String result = transService.depositTransaction(transaction);
        model.addAttribute("errormessage",result);
        model.addAttribute("transaction", new Transaction());
        return "transactiondeposit";
    }

    @GetMapping("/withdraw")
    public String Log(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "transactionwithdraw";
    }

    @PostMapping("/withdraw")
    public String PostWithdraw(Model model,@ModelAttribute Transaction transaction) {
        String result = transService.withdrawTransaction(transaction);
        model.addAttribute("errormessage",result);
        model.addAttribute("transaction", new Transaction());
        return "transactionwithdraw";
    }
    @GetMapping("/log")
    public String Withdraw(Model model) {
        model.addAttribute("listall", transService.getTransactions());
        return "transactionlog";
    }

    @GetMapping("/transhome")
    public String Home(HttpSession session) {
        return "transactionhome";
    }
}
