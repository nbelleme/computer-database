package com.excilys.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariDataSource;

public class Database {
  private String URL;
  private String DATABASE;
  private String OPTIONS;
  private String USER;
  private String PASSWORD;
  private String JDBC_DRIVER;
  private HikariDataSource poolConnection;

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

      poolConnection = new HikariDataSource();
      poolConnection.setJdbcUrl(URL + DATABASE + OPTIONS);
      poolConnection.setUsername(USER);
      poolConnection.setPassword(PASSWORD);
      // set chache of prepStmts (with recommended value from Hikari)
      poolConnection.addDataSourceProperty("cachePrepStmts", "true");
      // the default is 25
      poolConnection.addDataSourceProperty("prepStmtCacheSize", "10");
      // the default is 256
      poolConnection.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

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
      return poolConnection.getConnection();
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }

  }
}
