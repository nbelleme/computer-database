package com.excilys.validator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.dto.ComputerDTO;

@Component
@Scope("singleton")
public class ComputerDTOValidator {


  public void isValid(ComputerDTO computerDto){
    
  }
}
