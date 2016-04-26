package com.excilys.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
  private String URL;
  private String DATABASE;
  private String OPTIONS;
  private String USER;
  private String PASSWORD;
  private String JDBC_DRIVER;

  private static Database instance = null;

  private Database() {
    try {
      Properties props = new Properties();

      String fileName = "Database.properties";

      InputStream inputStream = Database.class.getClassLoader().getResourceAsStream(fileName);

      props.load(inputStream);
      URL = props.getProperty("URL");
      DATABASE = props.getProperty("DATABASE");
      OPTIONS = props.getProperty("OPTIONS");
      USER = props.getProperty("USER");
      PASSWORD = props.getProperty("PASSWORD");
      JDBC_DRIVER = props.getProperty("JDBC_DRIVER");

      Class.forName(JDBC_DRIVER);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * @return ComputerMapper instance of Database
   * @throws ClassNotFoundException
   */
  public static Database getInstance() {
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
  public Connection getConnection() throws DatabaseException {
    try {

      return DriverManager.getConnection(URL + DATABASE + OPTIONS, USER, PASSWORD);
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }

  }
}
