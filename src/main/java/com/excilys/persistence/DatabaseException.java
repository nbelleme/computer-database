package com.excilys.persistence;

@SuppressWarnings("serial")
public class DatabaseException extends RuntimeException {

  /**
   * @param e
   *          Exception thrown
   */
  public DatabaseException(Exception e) {
    super(e);
  }
}
