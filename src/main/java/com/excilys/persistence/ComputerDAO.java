package com.excilys.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Computer;
import com.mysql.jdbc.Statement;

public class ComputerDAO implements DAO<Computer> {
  public static final String COMPUTER_TABLE = "computer";
  public static final String COMPANY_TABLE = "company";
  private static final String ADD_QUERY = "INSERT INTO " + COMPUTER_TABLE
      + " (name, introduced, discontinued, company_id)" + " VALUES (?, ?, ?, ?)";
  private static final String DELETE_QUERY = "DELETE FROM " + COMPUTER_TABLE + " WHERE id = ? ";
  private static final String FIND_QUERY = "SELECT * FROM " + COMPUTER_TABLE + "  LEFT JOIN "
      + COMPANY_TABLE + " ON computer.company_id = company.id WHERE computer.id = ?;";
  private static final String FIND_ALL_QUERY = "SELECT * FROM " + COMPUTER_TABLE + " LEFT JOIN "
      + COMPANY_TABLE + " ON computer.company_id = company.id " + " LIMIT ?,?";
  private static final String UPDATE_QUERY = "UPDATE " + COMPUTER_TABLE
      + "SET name = ?, introduced = ?, discontinued = ?, company_id = ?";
  private static final String TOTAL_QUERY = "SELECT count(*) FROM " + COMPUTER_TABLE;

  private ComputerMapper mapper;
  private Database database;

  private static ComputerDAO instance = null;

  /**
   * @return instance instance of ComputerDAO
   */
  public static ComputerDAO getInstance() {
    if (instance == null) {
      synchronized (ComputerDAO.class) {
        if (instance == null) {
          instance = new ComputerDAO();
        }
      }
    }

    return instance;
  }

  /**
   * Default constructor, initialize mapper.
   */
  private ComputerDAO() {
    mapper = ComputerMapper.getMapper();
    database = Database.getInstance();

  }

  @Override
  public long add(Computer computer) throws DaoException, DatabaseException {
    try (Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(ADD_QUERY,
            Statement.RETURN_GENERATED_KEYS);) {
      long id = -1;
      mapper.map(computer, stmt);
      int affectedRows = stmt.executeUpdate();

      if (affectedRows == 0) {
        throw new DaoException(new SQLException("Creating computer failed, no rows affected."));
      }

      try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          id = generatedKeys.getLong(1);
        } else {
          throw new DaoException(new SQLException("Creating computer failed, no ID obtained."));
        }
      }
      return id;
    } catch (SQLException e) {
      throw new DaoException(e);
    } catch (DatabaseException e) {
      throw new DatabaseException(e);
    }

  }

  @Override
  public void delete(Computer computer) throws DaoException, DatabaseException {
    try (Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(DELETE_QUERY);) {
      stmt.setLong(1, computer.getId());
      stmt.executeUpdate();
    } catch (DatabaseException e) {
      throw new DatabaseException(e);
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public Computer find(long id) throws DaoException, DatabaseException {
    try (Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(FIND_QUERY);) {
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      rs.first();
      return mapper.unmap(rs);
    } catch (DatabaseException e) {
      throw new DatabaseException(e);
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public List<Computer> findSeveral(int firstRow, int countRow)
      throws DaoException, DatabaseException {
    try (Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(FIND_ALL_QUERY);) {
      stmt.setInt(1, firstRow);
      stmt.setInt(2, countRow);
      ResultSet rs = stmt.executeQuery();
      ArrayList<Computer> computers = new ArrayList<Computer>();
      while (rs.next()) {
        computers.add(mapper.unmap(rs));
      }
      return computers;
    } catch (DatabaseException e) {
      throw new DatabaseException(e);
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public void update(Computer computer) throws DaoException, DatabaseException {
    try (Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY);) {
      mapper.map(computer, stmt);
      stmt.executeUpdate();
    } catch (DatabaseException e) {
      throw new DatabaseException(e);
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public int getTotal() throws DaoException {
    try (Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(TOTAL_QUERY);) {
      ResultSet rs = stmt.executeQuery();
      rs.first();
      return rs.getInt(1);
    } catch (DatabaseException e) {
      throw new DatabaseException(e);
    } catch (SQLException e1) {
      throw new DaoException("Error request");
    }
  }
}
