package com.excilys.mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.CompanyDAO;

@Component
@Scope("singleton")
public class ComputerMapperDB implements Mapper<Computer> {

  public static final String COMPUTER_TABLE = "";
  public static final String COMPANY_TABLE = CompanyDAO.COMPANY_TABLE;
  public static final String ID = "computer.id";
  public static final String NAME = "name";
  public static final String INTRODUCED = "introduced";
  public static final String DISCONTINUED = "discontinued";
  public static final String COMPANY_ID = "company_id";
  public static final String COMPANY_NAME = "company.name";
  public static final String COMPANY_TABLE_ID = "company.id";
  public static final String COMPANY_TABLE_NAME = "company.name";

  @Override
  public void map(Computer entity, PreparedStatement stmt) throws SQLException {
    stmt.setString(1, entity.getName());

    if (entity.getIntroduced() != null) {
      stmt.setTimestamp(2, Timestamp.valueOf(entity.getIntroduced().atStartOfDay()));
    } else {
      stmt.setObject(2, null, java.sql.Types.TIMESTAMP);
    }
    if (entity.getDiscontinued() != null) {
      stmt.setTimestamp(3, Timestamp.valueOf(entity.getDiscontinued().atStartOfDay()));
    } else {
      stmt.setObject(3, null, java.sql.Types.TIMESTAMP);
    }
    if (entity.getCompany() != null) {
      stmt.setObject(4, entity.getCompany().getId(), java.sql.Types.BIGINT);
    } else {
      stmt.setObject(4, null, java.sql.Types.BIGINT);
    }
  }

  @Override
  public Computer unmap(ResultSet rs) throws SQLException {
    LocalDate introduced = rs.getTimestamp(INTRODUCED) == null ? null
        : rs.getTimestamp(INTRODUCED).toLocalDateTime().toLocalDate();
    LocalDate discontinued = rs.getTimestamp(DISCONTINUED) == null ? null
        : rs.getTimestamp(DISCONTINUED).toLocalDateTime().toLocalDate();
    Company company = null;
    if ((Long) rs.getLong(COMPANY_ID) != 0) {
      company = new Company((Long) rs.getLong(COMPANY_ID));
    }
    return new Computer.Builder().id(rs.getLong(ID)).name(rs.getString(NAME)).introduced(introduced)
        .discontinued(discontinued).company(company).build();
  }

}