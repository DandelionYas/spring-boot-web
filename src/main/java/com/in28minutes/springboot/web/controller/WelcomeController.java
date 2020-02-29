package com.in28minutes.springboot.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

//@RestController // = @Controller + @ResponseBody
@Controller
@SessionAttributes("name")
public class WelcomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
//    @ResponseBody //This annotation prevents DispatcherServlet from looking for view
                    //And returns response value directly to the client
//    @GetMapping("/login") //It also contains @ResponseBody
    public String showWelcomePage(ModelMap model) {
        // Because name is a minor detail of the user and does not take a lot of space we put it in session
        model.put("name", getLoggedInUsername());
        return "welcome";
    }

    private String getLoggedInUsername() {
        // In the Spring Security terminology 'Principal' is the LoggedIn User
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }
}