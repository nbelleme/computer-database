package com.excilys.persistence;

public class DatabaseException extends RuntimeException {

  private static final long serialVersionUID = 4120903544176738859L;

  /**
   * @param e
   *          Exception thrown
   */
  public DatabaseException(Exception e) {
    super(e);
  }
}
