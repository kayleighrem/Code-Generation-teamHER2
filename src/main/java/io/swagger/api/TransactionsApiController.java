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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    public List<Transaction> getTransactions(@Min(1)@ApiParam(value = "The user's id", allowableValues = "") @Valid @RequestParam(value = "userid", required = false) Integer userid
, @Min(1)@ApiParam(value = "The id of a specific transaction", allowableValues = "") @Valid @RequestParam(value = "transactionid", required = false) Integer transactionid
) {
        return transService.getTransactions();
    }



    public void newTransaction(@ApiParam(value = "Transaction object" ,required=true )  @Valid  Transaction body
, @NotNull @Min(1)@ApiParam(value = "ID of user performing request", required = true, allowableValues = "") @Valid @RequestParam(value = "id", required = false) Integer id
, @RequestBody Transaction transaction)
    {
        transService.newTransaction(transaction);
    }

    public void postDeposit(@ApiParam(value = "The user's id" ,required=true, allowableValues="") @RequestHeader(value="userid", required=true) Integer userid
, @ApiParam(value = "The amount to deposit" ,required=true) @RequestHeader(value="amount", required=true) Double amount
            , @RequestBody Transaction transaction)
    {
        transService.depositTransaction(transaction);
    }

    public void postWithdraw(@ApiParam(value = "The user's id" ,required=true, allowableValues="") @RequestHeader(value="userid", required=true) Integer userid
,@ApiParam(value = "The amount to withdraw" ,required=true) @RequestHeader(value="amount", required=true) Double amount
            , @RequestBody Transaction transaction)
    {
        transService.getTransactions();
    }


    @GetMapping("/test")
    public String greetingForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "testing";
    }

    @PostMapping("/test")
    public String transactionSubmit(@ModelAttribute Transaction transaction) {
        transService.newTransaction(transaction);

        return "result";
    }

    @GetMapping("/login")
    public String login() {


        return "login";
    }


}
