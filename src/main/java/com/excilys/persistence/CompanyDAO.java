package com.excilys.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.excilys.mapper.CompanyMapperDB;
import com.excilys.model.Company;

@Repository
@Scope("singleton")
public class CompanyDAO implements DAO<Company> {
  public static final String COMPANY_TABLE = "company";

  private static final String FIND_QUERY = "SELECT id, name FROM " + COMPANY_TABLE
      + " WHERE id = ?";
  private static final String FIND_SEVERAL_QUERY = "SELECT * " + "FROM " + COMPANY_TABLE
      + " LIMIT ?,?";
  private static final String FIND_ALL_QUERY = "SELECT * " + "FROM " + COMPANY_TABLE;

  public static final String DELETE_COMPANY = "DELETE FROM " + COMPANY_TABLE + " WHERE id = ?";

  Logger logger = LoggerFactory.getLogger(DaoException.class);

  @Autowired
  private CompanyMapperDB mapper;

  @Autowired
  private Database database;

  @Override
  public long add(Company item) throws DaoException {
    long id = -1;
    return id;
  }

  @Override
  public void delete(Company item) throws DaoException {
    Connection conn = database.getConnection();
    try {
      PreparedStatement stmtCompany = conn.prepareStatement(DELETE_COMPANY);
      stmtCompany.setLong(1, item.getId());
      stmtCompany.executeUpdate();
    } catch (SQLException e) {
      logger.error(e.getMessage());
      throw new DaoException(e);
    }
  }

  @Override
  public Company find(long id) throws DaoException {
    Connection connection = database.getConnection();
    try (PreparedStatement stmt = connection.prepareStatement(FIND_QUERY);) {
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      rs.first();
      return mapper.unmap(rs);
    } catch (SQLException e) {
      logger.error(e.getMessage());
      throw new DaoException(e);
    }
  }

  @Override
  public List<Company> findSeveral(int firstRow, int countRow) throws DaoException {
    Connection connection = database.getConnection();
    try (PreparedStatement stmt = connection.prepareStatement(FIND_SEVERAL_QUERY);) {
      stmt.setInt(1, firstRow);
      stmt.setInt(2, countRow);
      ResultSet rs = stmt.executeQuery();
      ArrayList<Company> companies = new ArrayList<Company>();
      while (rs.next()) {
        companies.add(mapper.unmap(rs));
      }
      return companies;
    } catch (SQLException e) {
      logger.error(e.getMessage());
      throw new DaoException(e);
    }
  }

  public List<Company> findAll() {
    Connection connection = database.getConnection();
    try (PreparedStatement stmt = connection.prepareStatement(FIND_ALL_QUERY);) {
      ResultSet rs = stmt.executeQuery();
      ArrayList<Company> companies = new ArrayList<Company>();
      while (rs.next()) {
        companies.add(mapper.unmap(rs));
      }
      return companies;
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public void update(Company item) throws DaoException {

  }

  @Override
  public int getTotal() throws DaoException {
    return 0;
  }
}
