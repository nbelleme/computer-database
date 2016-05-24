package com.excilys.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.excilys.mapper.ComputerMapperDB;
import com.excilys.model.Computer;
import com.excilys.validator.ComputerDTOValidator;
import com.mysql.jdbc.Statement;

@Repository
@Scope("singleton")

public class ComputerDAO implements DAO<Computer> {
  public static final String COMPUTER_TABLE = "computer";
  public static final String COMPANY_TABLE = "company";
  private static final String ADD_QUERY = "INSERT INTO " + COMPUTER_TABLE
      + " (name, introduced, discontinued, company_id)" + " VALUES (?, ?, ?, ?)";
  private static final String DELETE_QUERY = "DELETE FROM " + COMPUTER_TABLE + " WHERE id = ? ";
  private static final String FIND_QUERY = "SELECT * FROM " + COMPUTER_TABLE + "  LEFT JOIN "
      + COMPANY_TABLE + " ON computer.company_id = company.id WHERE computer.id = ?";
  private static final String FIND_SEVERAL_QUERY = "SELECT * FROM " + COMPUTER_TABLE + " LEFT JOIN "
      + COMPANY_TABLE + " ON computer.company_id = company.id " + " LIMIT ?,?";
  private static final String UPDATE_QUERY = "UPDATE " + COMPUTER_TABLE
      + " SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
  private static final String TOTAL_QUERY = "SELECT count(*) FROM " + COMPUTER_TABLE;
  private static final String FIND_BY_NAME_OR_COMPANY = "SELECT * FROM " + COMPUTER_TABLE
      + " LEFT JOIN " + COMPANY_TABLE + " ON computer.company_id = company.id "
      + "WHERE computer.name LIKE ? or company.name LIKE ? ORDER BY %s %s LIMIT ?,?";

  private static final String FIND_SEARCH = "SELECT * FROM " + COMPUTER_TABLE + " LEFT JOIN "
      + COMPANY_TABLE + " ON computer.company_id = company.id ";

  private static final String NUMBER_BY_NAME_OR_COMPANY = "SELECT count(computer.id) FROM "
      + COMPUTER_TABLE + " LEFT JOIN " + COMPANY_TABLE + " ON computer.company_id = company.id ";

  public static final String DELETE_WHERE_COMPANY = "DELETE FROM " + COMPUTER_TABLE
      + " WHERE company_id = ?";

  @Autowired
  private ComputerMapperDB mapper;
  @Autowired
  private Database database;
  @Autowired
  private DataSource dataSource;

  private Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

  @Override
  public long add(Computer computer) {
    logger.debug("ComputerDAO ---- add");
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    GeneratedKeyHolder generatedKeysHolder = new GeneratedKeyHolder();
    long out = jdbcTemplate.update((Connection con) -> {
      PreparedStatement stmt = con.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS);
      mapper.map(computer, stmt);
      return stmt;
    }, generatedKeysHolder);

    long id;
    if (out != 0) {
      id = generatedKeysHolder.getKey().longValue();
    } else {
      throw new DaoException(new SQLException("Creating computer failed, no ID obtained."));
    }
    return id;

  }

  @Override
  public void delete(Computer computer) {
    logger.debug("ComputerDAO ---- delete");
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    try {
      jdbcTemplate.update(DELETE_QUERY, computer.getId());
    } catch (DataAccessException e) {
      throw new DaoException(e);
    }
  }

  public void deleteFromCompanyId(long id) {
    logger.debug("ComputerDAO ---- deleteFromCompanyId");
    Connection connection = database.getConnection();
    try (PreparedStatement stmt = connection.prepareStatement(DELETE_WHERE_COMPANY);) {
      stmt.setLong(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException(e);
    }
  }

  @Override
  public Computer find(long id) {
    logger.debug("ComputerDAO ---- find");
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    Object[] args = { id };
    try {
      return jdbcTemplate.queryForObject(FIND_QUERY, args, (ResultSet rs, int rownum) -> {
        rs.first();
        return mapper.unmap(rs);
      });
    } catch (DataAccessException e) {
      logger.error("ComputerDAO Exception ---- " + e.getMessage());
      throw new DaoException(e);
    }

  }

  public List<Computer> findBySearch(SearchComputer search) {
    logger.debug("ComputerDAO ---- findBySearch");
    String query = FIND_SEARCH;

    if (search.getNameToSearch() != null && search.getNameToSearch() != "") {
      query = query + " WHERE computer.name LIKE '" + search.getNameToSearch()
          + "%' OR company.name LIKE '" + search.getNameToSearch() + "%' ";
    }
    if (search.getOrder() != null) {
      query = query + " ORDER BY " + search.getOrder().getColumn();
      if (search.getSort() != null) {
        query = query + " " + search.getSort();
      }
    }
    query = query + " LIMIT ?, ?";
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    try {

      Object[] args = { search.getPage().getFirsRow(), search.getPage().getNbElementPage() };
      return jdbcTemplate.queryForObject(query, args, (ResultSet rs, int rowNum) -> {
        List<Computer> computers = new ArrayList<>();
        rs.beforeFirst();
        while (rs.next()) {
          computers.add(mapper.unmap(rs));
        }
        return computers;
      });

    } catch (DataAccessException e) {
      throw new DaoException(e);
    }
  }

  public int getNumberFindBySearch(SearchComputer search) {
    logger.debug("ComputerDAO ---- getNumberFindBySearch");
    String query = NUMBER_BY_NAME_OR_COMPANY;

    if (search.getNameToSearch() != null && search.getNameToSearch() != "") {
      query = query + " WHERE  computer.name LIKE '" + search.getNameToSearch()
          + "%' OR company.name LIKE '" + search.getNameToSearch() + "%' ";
    }

    if (search.getOrder() != null) {
      query = query + " ORDER BY " + search.getOrder().getColumn();
      if (search.getSort() != null) {
        query = query + " " + search.getSort();
      }
    }

    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    try {
      return jdbcTemplate.queryForObject(query, (ResultSet rs, int rowNum) -> {
        rs.first();
        return rs.getInt(1);
      });
    } catch (DataAccessException e) {
      logger.error(query);
      throw new DaoException(e);
    }
  }

  @Override
  public int getTotal() {
    logger.debug("ComputerDAO ---- getTotal");
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    return jdbcTemplate.queryForObject(TOTAL_QUERY, (ResultSet rs, int rowNum) -> {
      rs.first();
      return rs.getInt(1);
    });
  }

  @Override
  public void update(Computer computer) {
    logger.debug("ComputerDAO ---- update");
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    Object[] args = { computer.getName(),
        computer.getIntroduced() != null ? computer.getIntroduced() : null,
        computer.getDiscontinued() != null ? computer.getDiscontinued() : null,
        computer.getCompany() != null ? computer.getCompany().getId() : null, computer.getId() };
    try {
      jdbcTemplate.update(UPDATE_QUERY, args);
    } catch (DataAccessException e) {
      throw new DaoException(e);
    }
  }
}
