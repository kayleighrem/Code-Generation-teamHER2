package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-06-24T13:49:09.238Z[GMT]")
@Api(value = "account", description = "the account API")
public interface AccountsApi {

    @ApiOperation(value = "show all accounts from user", nickname = "accountGet", notes = "", response = Account.class, authorizations = {
            @Authorization(value = "ApiKeyAuth")    }, tags={ "account", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "showing selected account", response = Account.class),
            @ApiResponse(code = 400, message = "failed", response = Account.class),
            @ApiResponse(code = 401, message = "no account exist", response = Account.class) })
    @RequestMapping(value = "/account",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Account> accountGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "IBAN", required = true) Account IBAN
    );


    @ApiOperation(value = "select type account", nickname = "accountPost", notes = "", authorizations = {
            @Authorization(value = "ApiKeyAuth")    }, tags={ "account", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "selected account has be created"),
            @ApiResponse(code = 401, message = "failed to create account.") })
    @RequestMapping(value = "/account",
            method = RequestMethod.POST)
    ResponseEntity<Void> accountPost(@ApiParam(value = "userID",required=true) @PathVariable("userID") String userID
            ,@NotNull @ApiParam(value = "Type of the new account", required = true) @Valid @RequestParam(value = "typeAcount", required = true) Account typeAcount
    );


    @ApiOperation(value = "deletes a account", nickname = "deleteAccount", notes = "", authorizations = {
            @Authorization(value = "ApiKeyAuth")    }, tags={ "account", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "succesfully account deleted"),
            @ApiResponse(code = 405, message = "vailed to delete the account") })
    @RequestMapping(value = "/account",
            method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteAccount();


    @ApiOperation(value = "Update a account", nickname = "updateAccount", notes = "", authorizations = {
            @Authorization(value = "ApiKeyAuth")    }, tags={ "account", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "account update success!"),
            @ApiResponse(code = 405, message = "account update invalid") })
    @RequestMapping(value = "/account",
            method = RequestMethod.PUT)
    ResponseEntity<Void> updateAccount();

}
