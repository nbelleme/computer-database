package com.excilys.validator;

import java.time.LocalDate;

import org.mockito.cglib.core.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.persistence.DaoException;

public enum ComputerDTOValidator {
  INSTANCE;
  private Logger logger = LoggerFactory.getLogger(ComputerDTOValidator.class);

  /**
   * Throws exception if id is superior to 0.
   *
   * @param id
   *          value to test
   */
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
   * Throws exception if date1 is after date2.
   *
   * @param date1
   *          first date.
   * @param date2
   *          last date.
   */
  public void isDateAfter(LocalDate date1, LocalDate date2) {

  }
}
