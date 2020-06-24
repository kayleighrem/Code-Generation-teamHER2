
package io.swagger.api.Api;

import io.swagger.annotations.*;
import io.swagger.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-20T13:24:55.413Z[GMT]")
@Api(value = "accounts", description = "the accounts API")
public interface AccountsApi {

    @ApiOperation(value = "show all accounts from user", nickname = "accountsGet", notes = "", response = Account.class, authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "account", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "showing selected account", response = Account.class),
        @ApiResponse(code = 400, message = "failed", response = Account.class),
        @ApiResponse(code = 401, message = "no account exist", response = Account.class) })
    @RequestMapping(value = "/accounts",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Account> accountsGet(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "accounts", required = true) Account enumAccounts
);


    @ApiOperation(value = "select type account", nickname = "accountsPost", notes = "", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "account", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "selected account has be created"),
        @ApiResponse(code = 400, message = "account already exist."),
        @ApiResponse(code = 401, message = "failed to create account.") })
    @RequestMapping(value = "/accounts",
        method = RequestMethod.POST)
    ResponseEntity<Void> accountsPost(@ApiParam(value = "Name of the new account",required=true) @PathVariable("accountName") String accountName
,@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "accounts", required = true) Account enumAccounts
,@ApiParam(value = "the ammount you want to save *optionel") @Valid @RequestParam(value = "target saving", required = false) Long targetSaving
);


    @ApiOperation(value = "deletes a account", nickname = "deleteAccount", notes = "", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "account", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "succesfully account deleted"),
        @ApiResponse(code = 405, message = "vailed to delete the account") })
    @RequestMapping(value = "/accounts",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteAccount();


    @ApiOperation(value = "Update a account", nickname = "updateAccount", notes = "", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "account", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "account update success!"),
        @ApiResponse(code = 405, message = "account update invalid") })
    @RequestMapping(value = "/accounts",
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateAccount();

}
