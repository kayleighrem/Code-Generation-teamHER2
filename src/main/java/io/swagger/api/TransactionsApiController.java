package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.api.Repositories.TransactionRepository;
import io.swagger.api.Services.TransactionService;
import io.swagger.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        transService.newTransaction(transaction);
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
    private Transaction currentTrans;

    @GetMapping("/transaction")
    public String greetingForm(Model model,HttpSession session) {
//        String warning = "of toch wel??";
        System.out.println(session.getAttribute("transaction"));
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("listall", transService.getTransactions());
//        model.addAttribute("warning",warning);
        return "transactionperform";
    }

    @PostMapping("/transaction")
    public String transactionSubmit(@ModelAttribute Transaction transaction, BindingResult result, HttpSession session) {
        transaction.setUserPerforming(2);

        transaction.status(Transaction.StatusEnum.ERROR);
        currentTrans = transaction;
        session.setAttribute("transaction",transaction);
        System.out.println(transaction);
        if (result.hasErrors()) {
            return "error"; //This should return some kind of error
        }
        String code = transService.newTransaction(transaction);
        return "result";
    }

    @GetMapping("/login")
    public String login(HttpSession session) {
        System.out.println(session.getAttribute("transaction"));
        return "login";
    }
}
