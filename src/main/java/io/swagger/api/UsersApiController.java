package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-20T13:24:55.413Z[GMT]")
@Controller
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> createUser(@ApiParam(value = "Created user object" ,required=true )  @Valid @RequestBody Users body
    ) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteUser(@ApiParam(value = "ID of user that needs to be deleted",required=true) @PathVariable("userId") Long userId
            ,@ApiParam(value = "ID of user that needs to be deleted" ,required=true) @RequestHeader(value="token", required=true) String token
    ) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Users>> getAllUsers() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Users>>(objectMapper.readValue("[ {\n  \"isEmployee\" : false,\n  \"name\" : \"Robin\",\n  \"userId\" : 35444,\n  \"email\" : \"robin.dejong@gmail.com\",\n  \"lastname\" : \"de Jong\"\n}, {\n  \"isEmployee\" : false,\n  \"name\" : \"Robin\",\n  \"userId\" : 35444,\n  \"email\" : \"robin.dejong@gmail.com\",\n  \"lastname\" : \"de Jong\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Users>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Users>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Users> getUserById(@Min(1)@ApiParam(value = "",required=true, allowableValues="") @PathVariable("userId") Integer userId
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Users>(objectMapper.readValue("{\n  \"isEmployee\" : false,\n  \"name\" : \"Robin\",\n  \"userId\" : 35444,\n  \"email\" : \"robin.dejong@gmail.com\",\n  \"lastname\" : \"de Jong\"\n}", Users.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Users>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Users>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateUser(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Users body
            ,@ApiParam(value = "ID of user that needs to be deleted" ,required=true) @RequestHeader(value="token", required=true) String token
            ,@ApiParam(value = "ID of user that needs to be updated",required=true) @PathVariable("userId") Long userId
    ) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Users> getUserByName(@ApiParam(value = "The name that needs to be fetched. Use user1 for testing.",required=true) @PathVariable("email") String email
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Users>(objectMapper.readValue("{\n  \"isEmployee\" : false,\n  \"name\" : \"Robin\",\n  \"userId\" : 35444,\n  \"email\" : \"robin.dejong@gmail.com\",\n \"password\" : \"testpwd\",\n  \"lastname\" : \"de Jong\"\n}", Users.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Users>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Users>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<String> loginUser(@NotNull @ApiParam(value = "The user name for login", required = true) @Valid @RequestParam(value = "email", required = true) String email
            ,@NotNull @ApiParam(value = "The password for login in clear text", required = true) @Valid @RequestParam(value = "password", required = true) String password
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<String>(objectMapper.readValue("\"\"", String.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> logoutUser() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }
}
