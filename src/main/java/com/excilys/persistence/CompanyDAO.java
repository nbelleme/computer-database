package com.excilys.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.mapper.CompanyMapperDB;
import com.excilys.model.Company;

public class CompanyDAO implements DAO<Company> {
  public static final String COMPANY_TABLE = "company";

  private static final String FIND_QUERY = "SELECT id, name FROM " + COMPANY_TABLE
      + " WHERE id = ?";
  private static final String FIND_SEVERAL_QUERY = "SELECT * " + "FROM " + COMPANY_TABLE
      + " LIMIT ?,?";
  private static final String FIND_ALL_QUERY = "SELECT * " + "FROM " + COMPANY_TABLE;

  Logger logger = LoggerFactory.getLogger(DaoException.class);

  private CompanyMapperDB mapper;
  private Database database;

  private static CompanyDAO instance = null;

  /**
   * Method to get the single instance of CompanyDAO.
   *
   * @return instance instance of CompanyDAO
   */
  public static CompanyDAO getInstance() {
    if (instance == null) {
      synchronized (CompanyDAO.class) {
        if (instance == null) {
          instance = new CompanyDAO();
        }
      }
    }

    return instance;
  }

  /**
   * Default constructor.
   */
  private CompanyDAO() {
    mapper = CompanyMapperDB.getMapper();
    database = Database.getInstance();
  }

  @Override
  public long add(Company item) throws DaoException {
    long id = -1;
    return id;
  }

  @Override
  public void delete(Company item) throws DaoException {

  }

  @Override
  public Company find(long id) throws DaoException {
    try (Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(FIND_QUERY);) {
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      rs.first();
      return mapper.unmap(rs);
    } catch (DatabaseException e) {
      logger.error(e.getMessage());
      throw new DatabaseException(e);
    } catch (SQLException e) {
      logger.error(e.getMessage());
      throw new DaoException(e);
    }
  }

  @Override
  public List<Company> findSeveral(int firstRow, int countRow) throws DaoException {
    try (Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(FIND_SEVERAL_QUERY);) {
      stmt.setInt(1, firstRow);
      stmt.setInt(2, countRow);
      ResultSet rs = stmt.executeQuery();
      ArrayList<Company> companies = new ArrayList<Company>();
      while (rs.next()) {
        companies.add(mapper.unmap(rs));
      }
      return companies;
    } catch (DatabaseException e) {
      logger.error(e.getMessage());
      throw new DatabaseException(e);
    } catch (SQLException e) {
      logger.error(e.getMessage());
      throw new DaoException(e);
    }
  }

  public List<Company> findAll() {
    try (Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(FIND_ALL_QUERY);) {
      ResultSet rs = stmt.executeQuery();
      ArrayList<Company> companies = new ArrayList<Company>();
      while (rs.next()) {
        companies.add(mapper.unmap(rs));
      }
      return companies;
    } catch (DatabaseException e) {
      throw new DatabaseException(e);
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public void update(Company item) throws DaoException {

  }

  @Override
  public int getTotal() throws DaoException {
    // TODO Auto-generated method stub
    return 0;
  }
}
