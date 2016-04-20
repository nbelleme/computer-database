package com.excilys.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.model.Company;
import com.excilys.model.Computer;

import mapper.CompanyMapper;

public class CompanyDAO implements DAO<Company> {
    private static final String TABLE = "company";
    private CompanyMapper mapper;

    public CompanyDAO() {
        mapper = CompanyMapper.getMapper();
    }

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
        try (Connection connection = Database.getConnection()) {
            String query = "SELECT * " + "FROM " + TABLE + " LIMIT ?,?";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, firstRow);
            stmt.setInt(2, countRow);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Company> companies = new ArrayList<Company>();
            while (rs.next()) {
                companies.add(mapper.unmap(rs));
            }
            return companies;
        }
    }

    @Override
    public void update(Company item) throws SQLException {
        // TODO Auto-generated method stub

    }
}
