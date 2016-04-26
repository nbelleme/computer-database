package com.excilys.persistence;

@SuppressWarnings("serial")
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
