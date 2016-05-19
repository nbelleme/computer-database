package com.excilys.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariDataSource;

@Component
@Scope("singleton")
public class Database {
  private String URL;
  private String DATABASE;
  private String OPTIONS;
  private String USER;
  private String PASSWORD;
  private String JDBC_DRIVER;
  private HikariDataSource poolConnection;
  private ThreadLocal<Connection> connectionLocal;

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

      Class.forName(JDBC_DRIVER);

      connectionLocal = new ThreadLocal<>();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void init() {
    try {
      Connection conn = poolConnection.getConnection();
      connectionLocal.set(conn);
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }
  }

  /**
   * @return Connection connection to database
   */
  public Connection getConnection() {
    return connectionLocal.get();
  }

  public void closeConnection() {
    if (connectionLocal.get() != null) {
      try {
        connectionLocal.get().close();
      } catch (SQLException e) {
        throw new DatabaseException(e);
      } finally {
        connectionLocal.remove();
      }
    }
  }

  public void setAutoCommit(boolean bool) {
    try {
      connectionLocal.get().setAutoCommit(bool);
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }
  }

  public void commit() {
    try {
      connectionLocal.get().commit();
    } catch (SQLException e) {
      rollback();
      throw new DatabaseException(e);
    }
  }

  public void rollback() {
    try {
      connectionLocal.get().rollback();
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }
  }

}
