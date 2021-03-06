package io.swagger.api.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.api.Api.UserApi;
import io.swagger.api.Services.UserService;
import io.swagger.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.SendFailedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-20T13:24:55.413Z[GMT]")
@Controller
public class UserApiController implements UserApi {

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;
    private HttpSession session;

    @Autowired
    private UserService userService;

    @org.springframework.beans.factory.annotation.Autowired
    public UserApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> createUser(@ApiParam(value = "Created user object" ,required=true )  @Valid @RequestBody User body
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

    public ResponseEntity<List<User>> getAllUser() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<User>>(objectMapper.readValue("[ {\n  \"isEmployee\" : false,\n  \"name\" : \"Robin\",\n  \"userId\" : 35444,\n  \"email\" : \"robin.dejong@gmail.com\",\n  \"lastname\" : \"de Jong\"\n}, {\n  \"isEmployee\" : false,\n  \"name\" : \"Robin\",\n  \"userId\" : 35444,\n  \"email\" : \"robin.dejong@gmail.com\",\n  \"lastname\" : \"de Jong\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<User>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<User> getUserById(@Min(1)@ApiParam(value = "",required=true, allowableValues="") @PathVariable("userId") Integer userId
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<User>(objectMapper.readValue("{\n  \"isEmployee\" : false,\n  \"name\" : \"Robin\",\n  \"userId\" : 35444,\n  \"email\" : \"robin.dejong@gmail.com\",\n  \"lastname\" : \"de Jong\"\n}", User.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateUser(@ApiParam(value = "" ,required=true )  @Valid @RequestBody User body
            ,@ApiParam(value = "ID of user that needs to be deleted" ,required=true) @RequestHeader(value="token", required=true) String token
            ,@ApiParam(value = "ID of user that needs to be updated",required=true) @PathVariable("userId") Long userId
    ) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<User> getUserByName(@ApiParam(value = "The name that needs to be fetched. Use user1 for testing.",required=true) @PathVariable("email") String email
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<User>(objectMapper.readValue("{\n  \"isEmployee\" : false,\n  \"name\" : \"Robin\",\n  \"userId\" : 35444,\n  \"email\" : \"robin.dejong@gmail.com\",\n \"password\" : \"testpwd\",\n  \"lastname\" : \"de Jong\"\n}", User.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
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

    @RequestMapping(value="/index" , method=RequestMethod.GET)
    public String Index(@ModelAttribute("user") User user, Model model, HttpSession session)  {
        try { // look if there's a session
//            HttpSession session=request.getSession();
            if(session!=null){ // if there is a session, go to the index page
                Navbar(model,session);
                return "index";
            }
        }
        catch (NullPointerException e) { // if no one is logged in (no session), go to the login page
            return "login";
        };
        return "";
    }

    @GetMapping("/register")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value="/register" , method=RequestMethod.POST)
    public String processLoginInfo(@ModelAttribute("user") User user, Model model) throws SendFailedException {
        String error = "Er bestaat al een gebruiker met dit emailadres.";
        if (userService.CheckIfEmailExists(user.getEmail()) == true)
            model.addAttribute("errormessage", error);
        else {
            userService.registerNewUserAccount(user);
            return "login";
        }
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value="/login" , method=RequestMethod.POST)
    public String LoginInfo(@ModelAttribute("user") User user, Model model,HttpSession session)  {
        String error = "username of password niet juist";
        if ( userService.CheckInlog(user) != null) {
            User u = userService.CheckInlog(user);
            session.setAttribute("loggedin_user",u);
            model.addAttribute("loggedin_user",u);
            Navbar(model,session);
            String redirectUrl = "index";
            return "redirect:" + redirectUrl;
        }
        else {
            model.addAttribute("errormessage", error);
            return "login";
        }
    }

    @GetMapping("/transactionhome")
    public String transactionhome(HttpSession session) {
        return "transactionhome";
    }

    @GetMapping("/employees")
    public String Employees(HttpSession session, Model model) {
        Navbar(model,session);
        model.addAttribute("allclients", userService.getClients());
        model.addAttribute("allemployees", userService.getEmployees());
        
        return "employees";
    }

    @RequestMapping(value = "/delete_user", method = RequestMethod.GET)
    public String handleDeleteUser(@RequestParam(name="userId")String userId) {
        int uid = Integer.parseInt(userId);
        userService.deleteUser(uid);

        String redirectUrl = "employees";
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/newuser")
    public String newUserForm(Model model, HttpSession session) {
        Navbar(model,session);
        model.addAttribute("user", new User());
        return "newuser";
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public String newUser(Model model, User user, HttpSession session) throws  SendFailedException{

        String error = "Er bestaat al een gebruiker met dit emailadres.";
        if (userService.CheckIfEmailExists(user.getEmail()) == true)
            model.addAttribute("errormessage", error);
        else {
            Navbar(model,session);
            userService.registerNewUserAccount(user);
            String redirectUrl = "employees";
            return "redirect:" + redirectUrl;
        }
        Navbar(model,session);
        return "newuser";
    }

    public void Navbar(Model model,HttpSession session) {
        User currentUser = (User) session.getAttribute("loggedin_user");
        String fullname = currentUser.getName() + " " + currentUser.getLastname();

        System.out.println(currentUser);
        model.addAttribute("session_fullname", fullname);
        model.addAttribute("session_isemployee", currentUser.getIsEmployee());
    }
}