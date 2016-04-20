package com.excilys.persistence;

import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.model.Company;

import mapper.CompanyMapper;

public class CompanyDAO implements DAO<Company> {
    private static final String TABLE = "company";
    private CompanyMapper mapper;

    @Override
    public void add(Company item) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Company item) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public Company find(Company entity) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Company> findAll(int firstRow, int countRow) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(Company item) throws SQLException {
        // TODO Auto-generated method stub

    }
}
