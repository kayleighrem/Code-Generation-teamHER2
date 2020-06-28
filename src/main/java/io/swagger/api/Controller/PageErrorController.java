package io.swagger.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

//Routes any whitelabel error message to the index page.
@Controller
public class PageErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

    private static final String PATH = "/error";

    @Autowired
    HttpSession session;

    @RequestMapping(value = PATH)
    public String error() {
        if(session.getAttribute("loggedin_user")==null)
            return "redirect:" + "login";
        else
        return "redirect:" + "account";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}