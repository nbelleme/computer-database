package com.excilys.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariDataSource;

@Component
@Scope("singleton")
public class Database {

  @Autowired
  private HikariDataSource ds;

  /**
   * @return Connection connection to database
   */
  public Connection getConnection() {
    try {
      return ds.getConnection();
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }
  }

}
