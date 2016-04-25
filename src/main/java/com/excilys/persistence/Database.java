package com.excilys.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
  private static final String URL = "jdbc:mysql://localhost/";
  private static final String USER = "admincdb";
  private static final String PASSWORD = "qwerty1234";
  private static final String DATABASE = "computer-database-db";
  private static final String OPTIONS = "?zeroDateTimeBehavior=convertToNull";

  private static Database instance = null;

  /**
   * @return ComputerMapper instance of Database
   */
  public static synchronized Database getInstance() {
    if (instance == null) {
      synchronized (Database.class) {
        if (instance == null) {
          instance = new Database();
        }
      }
    }
    return instance;
  }

  /**
   * @return Connection connection to database
   * @throws DatabaseException
   *           DatabaseException
   */
  public static Connection getConnection() throws DatabaseException {
    try {
      return DriverManager.getConnection(URL + DATABASE + OPTIONS, USER, PASSWORD);
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }

  }
}
