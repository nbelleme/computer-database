//package com.excilys.persistence;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.sql.DataSource;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.dao.DataAccessException;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.stereotype.Repository;
//
//import com.excilys.mapper.ComputerMapperDB;
//import com.excilys.model.Computer;
//import com.mysql.jdbc.Statement;
//
//@Repository
//@Scope("singleton")
//public class ComputerDAO {
//  public static final String COMPUTER_TABLE = "computer";
//  public static final String COMPANY_TABLE = "company";
//  private static final String ADD_QUERY = "INSERT INTO " + COMPUTER_TABLE
//      + " (name, introduced, discontinued, company_id)" + " VALUES (?, ?, ?, ?)";
//  private static final String DELETE_QUERY = "DELETE FROM " + COMPUTER_TABLE + " WHERE id = ? ";
//  private static final String FIND_QUERY = "SELECT * FROM " + COMPUTER_TABLE + "  LEFT JOIN "
//      + COMPANY_TABLE + " ON computer.company_id = company.id WHERE computer.id = ?";
//  private static final String UPDATE_QUERY = "UPDATE " + COMPUTER_TABLE
//      + " SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
//  private static final String TOTAL_QUERY = "SELECT count(*) FROM " + COMPUTER_TABLE;
//
//  private static final String FIND_SEARCH = "SELECT * FROM " + COMPUTER_TABLE;
//
//  private static final String NUMBER_BY_NAME_OR_COMPANY = "SELECT count(computer.id) FROM "
//      + COMPUTER_TABLE + " LEFT JOIN " + COMPANY_TABLE + " ON computer.company_id = company.id ";
//
//  public static final String DELETE_WHERE_COMPANY = "DELETE FROM " + COMPUTER_TABLE
//      + " WHERE company_id = ?";
//
//  @Autowired
//  private ComputerMapperDB mapper;
//  @Autowired
//  private DataSource dataSource;
//
//  private boolean isCacheCountOk = false;
//  private long count = 0;
//
//  private boolean isCacheTotalOk = false;
//  private long total = 0;
//
//  private Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
//
//  public long add(Computer computer) {
//    logger.debug("ComputerDAO ---- add");
//    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//    GeneratedKeyHolder generatedKeysHolder = new GeneratedKeyHolder();
//    long out = 0;
//    try {
//      out = jdbcTemplate.update((Connection con) -> {
//        PreparedStatement stmt = con.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS);
//        mapper.map(computer, stmt);
//        return stmt;
//      }, generatedKeysHolder);
//    } catch (Exception e) {
//      logger.debug("Exception : " + e.getMessage());
//      throw new DaoException(e);
//    }
//
//    long id;
//    if (out != 0) {
//      id = generatedKeysHolder.getKey().longValue();
//    } else {
//      logger.debug("Computer not added");
//      throw new DaoException(new SQLException("Creating computer failed, no ID obtained."));
//    }
//
//    isCacheCountOk = false;
//    isCacheTotalOk = false;
//
//    logger.debug("Computer added " + computer.toString());
//    return id;
//
//  }
//
//  public void delete(Computer computer) {
//    logger.debug("ComputerDAO ---- delete");
//    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//    try {
//      jdbcTemplate.update(DELETE_QUERY, computer.getId());
//    } catch (DataAccessException e) {
//      throw new DaoException(e);
//    }
//
//    isCacheCountOk = false;
//    isCacheTotalOk = false;
//  }
//
//  public void deleteFromCompanyId(long id) {
//    // logger.debug("ComputerDAO ---- deleteFromCompanyId");
//    // Connection connection = database.getConnection();
//    // try (PreparedStatement stmt = connection.prepareStatement(DELETE_WHERE_COMPANY);) {
//    // stmt.setLong(1, id);
//    // stmt.executeUpdate();
//    // } catch (SQLException e) {
//    // throw new DaoException(e);
//    // }
//  }
//
//  public Computer find(long id) {
//    logger.debug("ComputerDAO ---- find");
//    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//    Object[] args = { id };
//    try {
//      return jdbcTemplate.queryForObject(FIND_QUERY, args, (ResultSet rs, int rownum) -> {
//        rs.first();
//        return mapper.unmap(rs);
//      });
//    } catch (DataAccessException e) {
//      logger.error("ComputerDAO Exception ---- " + e.getMessage());
//      throw new DaoException(e);
//    }
//  }
//
//  public List<Computer> findBySearch(SearchComputer search) {
//    logger.debug("ComputerDAO ---- findBySearch");
//    String query = FIND_SEARCH;
//    if(search.getOrder() != ComputerColumns.COMPANY && search.getOrder() != ComputerColumns.ID){
//      query += " force index("+search.getOrder().getName()+")";
//    }
//    
//    if (search.getNameToSearch() != null && search.getNameToSearch() != "") {
//      query += " WHERE computer.name LIKE '" + search.getNameToSearch() + "%'";
//      query += search.createQuery();
//    }
//    if (search.getOrder() != null) {
//      query = query + " ORDER BY " + search.getOrder().getColumn();
//      if (search.getSort() != null) {
//        query = query + " " + search.getSort();
//      }
//    }
//    query = query + " LIMIT ?, ?";
//    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//    try {
//      Object[] args = { search.getPage().getFirsRow(), search.getPage().getPageSize() };
//      return jdbcTemplate.queryForObject(query, args, (ResultSet rs, int rowNum) -> {
//        List<Computer> temp = new ArrayList<>();
//        rs.beforeFirst();
//        while (rs.next()) {
//          temp.add(mapper.unmap(rs));
//        }
//        return temp;
//      });
//    } catch (EmptyResultDataAccessException e) {
//      return new ArrayList<>();
//    } catch (DataAccessException e) {
//      throw new DaoException(e);
//    }
//  }
//
//  public long getNumberFindBySearch(SearchComputer search) {
//    if (!isCacheCountOk) {
//      logger.debug("ComputerDAO ---- getNumberFindBySearch from database");
//      String query = NUMBER_BY_NAME_OR_COMPANY;
//
//      if (search.getNameToSearch() != null && search.getNameToSearch() != "") {
//        query = query + " WHERE  computer.name LIKE '" + search.getNameToSearch()
//            + "%' OR company.name LIKE '" + search.getNameToSearch() + "%' ";
//      }
//
//      if (search.getOrder() != null) {
//        query = query + " ORDER BY " + search.getOrder().getColumn();
//        if (search.getSort() != null) {
//          query = query + " " + search.getSort();
//        }
//      }
//
//      JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//      try {
//        count = jdbcTemplate.queryForObject(query, (ResultSet rs, int rowNum) -> {
//          rs.first();
//          return rs.getInt(1);
//        });
//      } catch (DataAccessException e) {
//        logger.error(query);
//        throw new DaoException(e);
//      }
//      isCacheCountOk = true;
//    } else {
//      logger.debug("ComputerDAO ---- getNumberFindBySearch from cache");
//    }
//
//    return count;
//
//  }
//
//  public long getTotal() {
//    if (!isCacheTotalOk) {
//      logger.debug("ComputerDAO ---- getTotal from database");
//      JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//      total = jdbcTemplate.queryForObject(TOTAL_QUERY, (ResultSet rs, int rowNum) -> {
//        rs.first();
//        return rs.getInt(1);
//      });
//      isCacheTotalOk = true;
//    } else {
//      logger.debug("ComputerDAO ---- getTotal from database");
//    }
//
//    return total;
//  }
//
//  public void update(Computer computer) {
//    logger.debug("ComputerDAO ---- update");
//    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//    Object[] args = { computer.getName(),
//        computer.getIntroduced() != null ? computer.getIntroduced() : null,
//        computer.getDiscontinued() != null ? computer.getDiscontinued() : null,
//        computer.getCompany() != null ? computer.getCompany().getId() : null, computer.getId() };
//    try {
//      jdbcTemplate.update(UPDATE_QUERY, args);
//    } catch (DataAccessException e) {
//      throw new DaoException(e);
//    }
//  }
//}
