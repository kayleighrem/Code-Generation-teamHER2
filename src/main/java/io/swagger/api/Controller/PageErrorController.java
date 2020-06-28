package io.swagger.api.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//Routes any whitelabel error message to the index page.
@Controller
public class PageErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "redirect:" + "index";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}