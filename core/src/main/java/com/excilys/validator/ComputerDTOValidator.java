package com.excilys.validator;

import com.excilys.dto.ComputerDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ComputerDTOValidator {


    public void isValid(ComputerDTO computerDto) {

    }
}
