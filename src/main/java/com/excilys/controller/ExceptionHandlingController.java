package com.excilys.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionHandlingController {

  @ExceptionHandler(NoHandlerFoundException.class)
  public String handlePageNotFoundException(NoHandlerFoundException ex) {
    return "errors/404";
  }

//  @ExceptionHandler(Exception.class)
//  public ModelAndView handleAllException(Exception ex) {
//    return new ModelAndView("errors/500", "message", ex.getMessage());
//  }
}
