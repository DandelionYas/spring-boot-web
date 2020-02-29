package com.in28minutes.springboot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/*
 * Inside this class we can add methods for handling specific Exceptions
 */
@Controller("error")
public class ErrorController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest request, Exception e) {
        ModelAndView modelAndView = new ModelAndView();

        // here we put exception information in the model
        modelAndView.addObject("exception", e.getStackTrace());
        modelAndView.addObject("url",  request.getRequestURL());
        // we can log this information in DB or log file

        modelAndView.setViewName("error");
        return modelAndView;
    }
}
