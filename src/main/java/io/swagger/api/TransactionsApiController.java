package io.swagger.api;

import io.swagger.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-20T13:24:55.413Z[GMT]")
@Controller
public class TransactionsApiController implements TransactionsApi {

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> getTransactions(@Min(1)@ApiParam(value = "The user's id", allowableValues = "") @Valid @RequestParam(value = "userid", required = false) Integer userid
,@Min(1)@ApiParam(value = "The id of a specific transaction", allowableValues = "") @Valid @RequestParam(value = "transactionid", required = false) Integer transactionid
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> newTransaction(@ApiParam(value = "Transaction object" ,required=true )  @Valid @RequestBody Transaction body
,@NotNull @Min(1)@ApiParam(value = "ID of user performing request", required = true, allowableValues = "") @Valid @RequestParam(value = "id", required = true) Integer id
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> postDeposit(@ApiParam(value = "The user's id" ,required=true, allowableValues="") @RequestHeader(value="userid", required=true) Integer userid
,@ApiParam(value = "The amount to deposit" ,required=true) @RequestHeader(value="amount", required=true) Double amount
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> postWithdraw(@ApiParam(value = "The user's id" ,required=true, allowableValues="") @RequestHeader(value="userid", required=true) Integer userid
,@ApiParam(value = "The amount to withdraw" ,required=true) @RequestHeader(value="amount", required=true) Double amount
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
