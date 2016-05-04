package com.excilys.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.mapper.ComputerMapperDB;
import com.excilys.model.Computer;
import com.excilys.validator.ComputerDTOValidator;
import com.mysql.jdbc.Statement;

public class ComputerDAO implements DAO<Computer> {
  public static final String COMPUTER_TABLE = "computer";
  public static final String COMPANY_TABLE = "company";
  private static final String ADD_QUERY = "INSERT INTO " + COMPUTER_TABLE
      + " (name, introduced, discontinued, company_id)" + " VALUES (?, ?, ?, ?)";
  private static final String DELETE_QUERY = "DELETE FROM " + COMPUTER_TABLE + " WHERE id = ? ";
  private static final String FIND_QUERY = "SELECT * FROM " + COMPUTER_TABLE + "  LEFT JOIN "
      + COMPANY_TABLE + " ON computer.company_id = company.id WHERE computer.id = ?;";
  private static final String FIND_SEVERAL_QUERY = "SELECT * FROM " + COMPUTER_TABLE + " LEFT JOIN "
      + COMPANY_TABLE + " ON computer.company_id = company.id " + " LIMIT ?,?";
  private static final String UPDATE_QUERY = "UPDATE " + COMPUTER_TABLE
      + " SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
  private static final String TOTAL_QUERY = "SELECT count(*) FROM " + COMPUTER_TABLE;
  private static final String FIND_BY_NAME_OR_COMPANY = "SELECT * FROM " + COMPUTER_TABLE
      + " LEFT JOIN " + COMPANY_TABLE + " ON computer.company_id = company.id "
      + "WHERE computer.name LIKE ? or company.name LIKE ? ORDER BY %s %s LIMIT ?,?";

  private static final String NUMBER_BY_NAME_OR_COMPANY = "SELECT count(*) FROM " + COMPUTER_TABLE
      + " LEFT JOIN " + COMPANY_TABLE + " ON computer.company_id = company.id "
      + "WHERE computer.name LIKE ? or company.name LIKE ?";

  private ComputerMapperDB mapper;
  private Database database;
  private Logger logger;

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
    mapper = ComputerMapperDB.getMapper();
    database = Database.getInstance();
    logger = LoggerFactory.getLogger(ComputerDTOValidator.class);
  }

  @Override
  public long add(Computer computer) {
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
    }

  }

  @Override
  public void delete(Computer computer) {
    try (Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(DELETE_QUERY);) {
      stmt.setLong(1, computer.getId());
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public Computer find(long id) {
    try (Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(FIND_QUERY);) {
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      rs.first();
      return mapper.unmap(rs);
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public List<Computer> findSeveral(int firstRow, int countRow) {
    try (Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(FIND_SEVERAL_QUERY);) {
      stmt.setInt(1, firstRow);
      stmt.setInt(2, countRow);
      ResultSet rs = stmt.executeQuery();
      List<Computer> computers = new ArrayList<Computer>();
      while (rs.next()) {
        computers.add(mapper.unmap(rs));
      }
      return computers;
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public void update(Computer computer) {
    try (Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY);) {
      stmt.setString(1, computer.getName());
      if (computer.getIntroduced() != null) {
        stmt.setTimestamp(2, Timestamp.valueOf(computer.getIntroduced().atStartOfDay()));
      } else {
        stmt.setObject(2, null, java.sql.Types.TIMESTAMP);
      }

      if (computer.getDiscontinued() != null) {
        stmt.setTimestamp(3, Timestamp.valueOf(computer.getDiscontinued().atStartOfDay()));
      } else {
        stmt.setObject(3, null, java.sql.Types.TIMESTAMP);
      }

      if (computer.getCompany() != null) {
        stmt.setObject(4, computer.getCompany().getId(), java.sql.Types.BIGINT);
      } else {
        stmt.setObject(4, null, java.sql.Types.BIGINT);
      }

      stmt.setLong(5, computer.getId());

      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public int getTotal() {
    try (Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(TOTAL_QUERY);) {
      ResultSet rs = stmt.executeQuery();
      rs.first();
      return rs.getInt(1);
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  public List<Computer> findByNameOrCompany(String name, String orderBy, String orderSort,
      int firstRow, int count) {
    try (Connection connection = database.getConnection();) {
      String str = String.format(FIND_BY_NAME_OR_COMPANY, orderBy, orderSort);
      PreparedStatement stmt = connection.prepareStatement(str);
      stmt.setString(1, '%' + name + '%');
      stmt.setString(2, '%' + name + '%');
      stmt.setInt(3, firstRow);
      stmt.setInt(4, count);

      ResultSet rs = stmt.executeQuery();
      List<Computer> computers = new ArrayList<Computer>();
      while (rs.next()) {
        computers.add(mapper.unmap(rs));
      }
      return computers;
    } catch (SQLException e) {
      logger.error("Error function : findByNameOrCompany");
      throw new DaoException(e);
    }
  }

  public int getNumberEntriesFindByNameOrCompany(String name) {
    try (Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(NUMBER_BY_NAME_OR_COMPANY);) {
      stmt.setString(1, '%' + name + '%');
      stmt.setString(2, '%' + name + '%');
      ResultSet rs = stmt.executeQuery();
      rs.first();
      return rs.getInt(1);
    } catch (SQLException e) {
      logger.error("Error function : findByNameOrCompany");
      throw new DaoException(e);
    }
  }
}
