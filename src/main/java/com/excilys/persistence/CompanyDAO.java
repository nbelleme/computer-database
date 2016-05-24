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
import org.springframework.stereotype.Repository;

import com.excilys.mapper.CompanyMapperDB;
import com.excilys.model.Company;

@Repository
@Scope("singleton")
public class CompanyDAO implements DAO<Company> {
  public static final String COMPANY_TABLE = "company";

  private static final String FIND_QUERY = "SELECT id, name FROM " + COMPANY_TABLE
      + " WHERE id = ?";
  private static final String FIND_ALL_QUERY = "SELECT * " + "FROM " + COMPANY_TABLE;

  public static final String DELETE_COMPANY = "DELETE FROM " + COMPANY_TABLE + " WHERE id = ?";

  Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

  @Autowired
  private CompanyMapperDB mapper;
  @Autowired
  private Database database;
  @Autowired
  private DataSource dataSource;

  @Override
  public long add(Company item) throws DaoException {
    logger.debug("CompanyDAO --- add");
    long id = -1;
    return id;
  }

  @Override
  public void delete(Company item) throws DaoException {
    logger.debug("CompanyDAO ---- delete");
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
    logger.debug("CompanyDAO ---- find");
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    try {
      Object[] args = { id };
      return jdbcTemplate.queryForObject(FIND_QUERY, args, (ResultSet rs, int rowNum) -> {
        rs.first();
        return mapper.unmap(rs);
      });
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new DaoException(e);
    }
  }

  public List<Company> findAll() {
    logger.debug("CompanyDAO ---- findAll");

    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    try {
      return jdbcTemplate.queryForObject(FIND_ALL_QUERY, (ResultSet rs, int rowNum) -> {
        List<Company> companies = new ArrayList<>();
        rs.first();
        while (rs.next()) {
          companies.add(mapper.unmap(rs));
        }
        return companies;
      });
    } catch (DataAccessException e) {
      logger.error("Error : " + e.getMessage());
      throw new DaoException(e);
    }
  }

  @Override
  public int getTotal() throws DaoException {
    logger.debug("CompanyDAO ---- getTotal");
    return 0;
  }

  @Override
  public void update(Company item) throws DaoException {
    logger.debug("CompanyDAO ---- update");

  }
}
