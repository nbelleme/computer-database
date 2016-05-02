package com.excilys.validator;

import com.excilys.model.Computer;

public enum ComputerValidator {

  INSTANCE;

  public boolean isValid(Computer computer) {
    if (computer.getName() != null) {
      if (computer.getIntroduced() != null) {
        if (computer.getDiscontinued() != null) {
          if (computer.getIntroduced().isBefore(computer.getDiscontinued())) {
            return true;
          }
        } else {
          return true;
        }
      }
    }
    return false;
  }

}
