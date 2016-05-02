package com.excilys.mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.model.Company;

public class CompanyMapperDB implements Mapper<Company> {
  public static final String ID = "id";
  public static final String NAME = "name";

  private static CompanyMapperDB instance = null;

  /**
   * @return ComputerMapper instance of CompanyMapper
   */
  public static CompanyMapperDB getMapper() {
    if (instance == null) {
      synchronized (CompanyMapperDB.class) {
        if (instance == null) {
          instance = new CompanyMapperDB();
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
