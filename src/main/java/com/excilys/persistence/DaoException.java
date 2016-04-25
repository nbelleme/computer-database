package com.excilys.persistence;

public class DaoException extends RuntimeException {

  /**
   * Default constructor.
   *
   * @param e
   *          Exception thrown
   */
  public DaoException(final Exception e) {
    super(e);
  }
}
