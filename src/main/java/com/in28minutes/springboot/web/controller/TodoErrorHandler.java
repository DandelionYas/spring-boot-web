package com.in28minutes.springboot.web.controller;

import com.in28minutes.springboot.web.exception.BadDescriptionFieldForTodoException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/*
 * Inside this class we can add methods for handling specific Exceptions
 * The other Exceptions will be handled by build in ResponseEntityExceptionHandler
 * If we wanted to handle all exceptions we must extend ResponseEntityExceptionHandler
 */
@ControllerAdvice
public class TodoErrorHandler {
    private final Log logger = LogFactory.getLog(getClass());

    @ExceptionHandler(BadDescriptionFieldForTodoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleException(HttpServletRequest request, Exception e) {
        ModelAndView modelAndView = new ModelAndView();

        // here we put exception information in the model
        modelAndView.addObject("exception", e.getClass().getSimpleName());
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.addObject("status", HttpStatus.BAD_REQUEST.value());
        modelAndView.addObject("error", HttpStatus.BAD_REQUEST.getReasonPhrase());

        // we can log this information in DB or log file
        logger.error(e.getStackTrace());

        modelAndView.setViewName("error");
        return modelAndView;
    }
}
