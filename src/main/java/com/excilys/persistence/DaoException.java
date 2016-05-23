package com.excilys.persistence;

public class DaoException extends RuntimeException {
  private static final long serialVersionUID = 3631455266081508130L;

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
