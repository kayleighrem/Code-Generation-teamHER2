/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.Transaction;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-20T13:24:55.413Z[GMT]")
@Api(value = "transactions", description = "the transactions API")
public interface TransactionsApi {

    @ApiOperation(value = "Retrieve list of all transactions with optional parameters", nickname = "getTransactions", notes = "", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Transactions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Retrieve Transaction data"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 405, message = "Bad Input") })
    @RequestMapping(value = "/transactions",
        method = RequestMethod.GET)
    List<Transaction> getTransactions(@Min(1)@ApiParam(value = "The user's id", allowableValues = "") @Valid @RequestParam(value = "userid", required = false) Integer userid
,@Min(1)@ApiParam(value = "The id of a specific transaction", allowableValues = "") @Valid @RequestParam(value = "transactionid", required = false) Integer transactionid
);


    @ApiOperation(value = "Performs a new transaction", nickname = "newTransaction", notes = "", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Transactions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Transaction success"),
        @ApiResponse(code = 405, message = "Bad Input") })
    @RequestMapping(value = "/transactions",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    void newTransaction(@ApiParam(value = "Transaction object" ,required=true )  @Valid @RequestBody Transaction body
,@NotNull @Min(1)@ApiParam(value = "ID of user performing request", required = true, allowableValues = "") @Valid @RequestParam(value = "id", required = true) Integer id
            , @RequestBody Transaction transaction);


    @ApiOperation(value = "Deposit an amount to the user's account", nickname = "postDeposit", notes = "", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Transactions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Deposit success"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "User ID not found"),
        @ApiResponse(code = 405, message = "Bad Input") })
    @RequestMapping(value = "/transactions/deposit",
        method = RequestMethod.POST)
    void postDeposit(@ApiParam(value = "The user's id" ,required=true, allowableValues="") @RequestHeader(value="userid", required=true) Integer userid
,@ApiParam(value = "The amount to deposit" ,required=true) @RequestHeader(value="amount", required=true) Double amount
            , @RequestBody Transaction transaction);


    @ApiOperation(value = "Withdraw an amount to the user's account", nickname = "postWithdraw", notes = "", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Transactions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Withdraw success"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "User ID not found"),
        @ApiResponse(code = 405, message = "Bad Input") })
    @RequestMapping(value = "/transactions/withdraw",
        method = RequestMethod.POST)
    void postWithdraw(@ApiParam(value = "The user's id" ,required=true, allowableValues="") @RequestHeader(value="userid", required=true) Integer userid
,@ApiParam(value = "The amount to withdraw" ,required=true) @RequestHeader(value="amount", required=true) Double amount
            , @RequestBody Transaction transaction);

}
