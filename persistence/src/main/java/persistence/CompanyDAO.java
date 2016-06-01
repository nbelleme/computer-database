package com.excilys.persistence;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.mapper.CompanyMapperDB;
import com.excilys.model.Company;

@Repository
@Scope("singleton")
public class CompanyDAO {
  public static final String COMPANY_TABLE = "company";

  private static final String FIND_QUERY = "SELECT id, name FROM " + COMPANY_TABLE
      + " WHERE id = ?";
  private static final String FIND_ALL_QUERY = "SELECT * " + "FROM " + COMPANY_TABLE;

  public static final String DELETE_COMPANY = "DELETE FROM " + COMPANY_TABLE + " WHERE id = ?";

  private boolean isCacheOk = false;
  private List<Company> companies = new ArrayList<>();

  Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

  @Autowired
  private CompanyMapperDB mapper;
  @Autowired
  private DataSource dataSource;

  public long add(Company item) throws DaoException {
    logger.debug("CompanyDAO --- add");
    long id = -1;
    return id;
  }

  public void delete(Company item) {
    // logger.debug("CompanyDAO ---- delete");
    // Connection conn = database.getConnection();
    // try {
    // PreparedStatement stmtCompany = conn.prepareStatement(DELETE_COMPANY);
    // stmtCompany.setLong(1, item.getId());
    // stmtCompany.executeUpdate();
    // } catch (SQLException e) {
    // logger.error(e.getMessage());
    // throw new DaoException(e);
    // }
  }

  public Company find(Long id) {
    if (!isCacheOk) {
      logger.debug("CompanyDAO ---- find from database");
      JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
      try {
        Object[] args = { id };
        return jdbcTemplate.queryForObject(FIND_QUERY, args, (ResultSet rs, int rowNum) -> {
          rs.first();
          return mapper.unmap(rs);
        });
      } catch (EmptyResultDataAccessException e) {
        throw new DaoException("Object not found");
      } catch (DataAccessException e) {
        logger.error(e.getMessage());
        throw new DaoException(e);
      }
    }

    Company company = new Company(id);
    if (companies.contains(company)) {
      logger.debug("CompanyDAO ---- find from cache");
      return companies.get(companies.indexOf(company));
    } else {
      throw new DaoException("Object " + company.toString() + " not found");
    }
  }

  public List<Company> findAll(int order) {
    if (!isCacheOk) {
      logger.debug("CompanyDAO ---- findAll from database");
      try {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String query = FIND_ALL_QUERY + " ORDER BY name ASC";
        companies = jdbcTemplate.queryForObject(query, (ResultSet rs, int rowNum) -> {
          List<Company> temp = new ArrayList<>();
          rs.beforeFirst();
          while (rs.next()) {
            Company company = mapper.unmap(rs);
            temp.add(company);
          }
          return temp;
        });

      } catch (DataAccessException e) {
        logger.error("Error : " + e.getMessage());
        throw new DaoException(e);
      }

      isCacheOk = true;

    } else

    {
      logger.debug("CompanyDAO ---- findAll from cache");
    }
    if (order == 1) {
      return companies;
    } else {
      List<Company> shallowCopy = companies.subList(0, companies.size());
      Collections.reverse(shallowCopy);
      return shallowCopy;
    }
  }

  public int getTotal() {
    logger.debug("CompanyDAO ---- getTotal");
    return 0;
  }

  public void update(Company item) {
    logger.debug("CompanyDAO ---- update");
  }

  public List<Company> findByName(String search, int order) {
    List<Company> companies = findAll(order);
    if (search == "" || search == null) {
      return companies;
    }
    List<Company> ids = new ArrayList<>();
    for (Company company : companies) {
      if (company.getName().toLowerCase().contains(search.toLowerCase())) {
        ids.add(company);
      }
    }
    return ids;
  }
}
