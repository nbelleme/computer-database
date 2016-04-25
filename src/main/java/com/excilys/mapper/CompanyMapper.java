package com.excilys.mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.model.Company;

public class CompanyMapper implements Mapper<Company> {
  public static final String ID = "id";
  public static final String NAME = "name";

  private static CompanyMapper instance = null;

  /**
   * @return ComputerMapper instance of CompanyMapper
   */
  public static CompanyMapper getMapper() {
    if (instance == null) {
      synchronized (CompanyMapper.class) {
        if (instance == null) {
          instance = new CompanyMapper();
        }
      }
    }
    return instance;
  }

  @Override
  public void map(Company company, PreparedStatement stmt) throws SQLException {
    stmt.setLong(1, company.getId());
    stmt.setString(2, company.getName());
  }

  @Override
  public Company unmap(ResultSet rs) throws SQLException {
    return new Company.Builder().id(rs.getLong(ID)).name(rs.getString(NAME)).build();
  }

}
