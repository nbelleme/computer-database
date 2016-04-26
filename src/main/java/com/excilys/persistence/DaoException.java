package com.excilys.persistence;

@SuppressWarnings("serial")
public class DaoException extends Exception {

  /**
   * Default constructor.
   *
   * @param e
   *          Exception thrown
   */
  public DaoException(final Exception e) {
    super(e);
  }

  public DaoException(String message) {
    super(message);
  }
}
