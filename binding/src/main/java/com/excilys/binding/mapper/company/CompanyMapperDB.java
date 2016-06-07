package com.excilys.binding.mapper.company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.binding.mapper.Mapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.model.Company;

@Component
@Scope("singleton")
public class CompanyMapperDB implements Mapper<Company> {
  public static final String ID = "id";
  public static final String NAME = "name";

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
