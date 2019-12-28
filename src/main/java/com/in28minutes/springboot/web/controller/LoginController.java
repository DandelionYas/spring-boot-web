package com.in28minutes.springboot.web.controller;

import com.in28minutes.springboot.web.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

//@RestController // = @Controller + @ResponseBody
@Controller
@SessionAttributes("name")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    @ResponseBody
//    @GetMapping("/login")
    public String showLoginPage(ModelMap model) {
        return "login";
    }

    @PostMapping("/login")
    public String showWelcomePage(ModelMap model,
                                  @RequestParam String name,
                                  @RequestParam String password) {
        if(!loginService.isValidUser(name, password)) {
            model.put("errorMessage", "Invalid credentials!");
            return "login";
        }

        model.put("name", name);
        model.put("password", password);
        return "welcome";
    }
}