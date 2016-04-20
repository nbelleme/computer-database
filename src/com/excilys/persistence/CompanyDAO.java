package com.excilys.persistence;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;

import com.excilys.model.Company;
import com.excilys.model.Computer;

import mapper.CompanyMapper;

public class CompanyDAO implements DAO<Company> {
    private static final String TABLE = "company";
    private CompanyMapper mapper;

    public CompanyDAO() {
        mapper = new CompanyMapper();
    }

    @Override
    public void add(Company company) throws SQLException {
        JdbcRowSet rowset = Database.getInstance().getRowSet();
        rowset.setCommand("SELECT * FROM " + TABLE);
        rowset.execute();
        rowset.moveToInsertRow();
        mapper.map(company, rowset);
        rowset.insertRow();
    }

    @Override
    public void delete(Company company) throws SQLException {
        JdbcRowSet rowset = Database.getInstance().getRowSet();
        rowset.setCommand("SELECT * FROM " + TABLE + " WHERE id = ?");
        rowset.setLong(1, company.getId());
        rowset.execute();
        rowset.last();
        rowset.deleteRow();

    }

    @Override
    public Company find(Long id) throws SQLException {
        JdbcRowSet rowset = Database.getInstance().getRowSet();
        rowset.setCommand("SELECT * FROM " + TABLE + " WHERE id = ?");
        rowset.setLong(1, id);
        rowset.execute();
        Company company = mapper.unmap(rowset);
        return company;
    }

    @Override
    public void update(Company company) throws SQLException {
        JdbcRowSet rowset = Database.getInstance().getRowSet();
        rowset.setCommand("SELECT * FROM computer WHERE id = ? ");
        rowset.setLong(1, company.getId());
        rowset.execute();

        mapper.map(company, rowset);
        rowset.updateRow();

    }

}
