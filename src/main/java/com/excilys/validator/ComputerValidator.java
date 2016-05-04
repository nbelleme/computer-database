package com.excilys.validator;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.model.Computer;

public enum ComputerValidator {

  INSTANCE;
  private Logger logger = LoggerFactory.getLogger(ComputerValidator.class);

  public void isIdValid(long id) {
    if (id <= 0) {
      logger.error("Error Computer Validator : id invalid");
      throw new ValidatorException("Error Computer Validator : id invalid");
    }
  }

  /**
   * Throws exception if name length is superior to 0 and if name is not null.
   *
   * @param name
   *          value to test
   */
  public void isNameValid(String name) {
    if (name == "" || name == null) {
      logger.error("Error Computer Validator : name invalid");
      throw new ValidatorException("Error Computer Validator : name invalid");
    }
  }

  /**
   * Throws exception if date is between timestamp's limits.
   *
   * @param date
   *          value to test
   */
  public void isDateConvertibleToTimestamp(LocalDate date) {
    if (date.isBefore(LocalDate.MIN) || date.isAfter(LocalDate.MAX)) {
      logger.error("Error Computer Validator : date invalid");
      throw new ValidatorException("Error Computer Validator : date invalid");
    }
  }

  /**
   * Throws exception if date1 is after date2. Throws exception if date2 is not null but date1 is
   * null.
   *
   * @param date1
   *          first date.
   * @param date2
   *          last date.
   */
  public void isDatesValid(LocalDate date1, LocalDate date2) {
    if (date1 == null && date2 != null) {
      throw new ValidatorException(
          "Error Computer Validator : date1 is null but date2 is not null");
    }

    if (date1 != null && date2 != null) {
      if (date1.isAfter(date2)) {
        throw new ValidatorException("Error Computer Validator : timeline invalid");
      }
    }

  }

  /**
   * Check if each parameter of computer is valid.
   *
   * @param computer
   *          computer to test
   */
  public void isValid(Computer computer) {
    isNameValid(computer.getName());
    if (computer.getIntroduced() != null) {
      isDateConvertibleToTimestamp(computer.getIntroduced());
    }
    if (computer.getDiscontinued() != null) {
      isDateConvertibleToTimestamp(computer.getDiscontinued());
    }
    isDatesValid(computer.getIntroduced(), computer.getDiscontinued());
  }

}
