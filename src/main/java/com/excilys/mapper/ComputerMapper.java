package com.excilys.mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.ComputerDAO;

public class ComputerMapper implements Mapper<Computer> {

    public static final String COMPUTER_TABLE = ComputerDAO.COMPUTER_TABLE;
    public static final String COMPANY_TABLE = CompanyDAO.COMPANY_TABLE;
    public static final String ID = "computer.id";
    public static final String NAME = "name";
    public static final String INTRODUCED = "introduced";
    public static final String DISCONTINUED = "discontinued";
    public static final String COMPANY_ID = "company_id";
    public static final String COMPANY_NAME = "company.name";
    public static final String COMPANY_TABLE_ID = "company.id";
    public static final String COMPANY_TABLE_NAME = "company.name";

    private static ComputerMapper _instance = null;

    public static ComputerMapper getMapper() {
        if (_instance == null) {
            synchronized (ComputerMapper.class) {
                if (_instance == null) {
                    _instance = new ComputerMapper();
                }
            }
        }
        return _instance;
    }

    @Override
    public void map(Computer entity, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, entity.getName());
        stmt.setTimestamp(2, Timestamp.valueOf(entity.getIntroduced()));
        stmt.setTimestamp(3, Timestamp.valueOf(entity.getDiscontinued()));
        stmt.setLong(4, entity.getCompany().getId());
    }

    @Override
    public Computer unmap(ResultSet rs) throws SQLException {
        LocalDateTime introduced = rs.getTimestamp(INTRODUCED) == null ? null
                : rs.getTimestamp(INTRODUCED).toLocalDateTime();
        LocalDateTime discontinued = rs.getTimestamp(DISCONTINUED) == null ? null
                : rs.getTimestamp(INTRODUCED).toLocalDateTime();
        Company company = new Company.Builder().id(rs.getLong(COMPANY_TABLE_ID)).name(rs.getString(COMPANY_TABLE_NAME))
                .build();
        return new Computer.Builder().id(rs.getLong(ID)).name(rs.getString(NAME)).introduced(introduced)
                .discontinued(discontinued).company(company).build();
    }

}
