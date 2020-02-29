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

    @RequestMapping(value = "/", method = RequestMethod.GET)
//    @ResponseBody //This annotation prevents DispatcherServlet from looking for view
                    //And returns response value directly to the client
//    @GetMapping("/login") //It also contains @ResponseBody
    public String showLoginPage(ModelMap model) {
        model.put("name", "Yaser");
        return "welcome";
    }
}