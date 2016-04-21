package com.excilys.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.model.Computer;
import com.mysql.jdbc.Statement;

import mapper.ComputerMapper;

public class ComputerDAO implements DAO<Computer> {
    private static final String TABLE = "computer";
    private ComputerMapper mapper;

    public ComputerDAO() throws SQLException {
        mapper = ComputerMapper.getMapper();
    }

    @Override
    public long add(Computer computer) throws SQLException {
        long id = -1;
        try (Connection connection = Database.getConnection()) {
            String query = "INSERT INTO " + TABLE + " (name, introduced, discontinued, company_id)"
                    + " VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            mapper.map(computer, stmt);
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
        return id;
    }

    @Override
    public void delete(Computer computer) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String query = "DELETE FROM " + TABLE + " WHERE id = ? ";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, computer.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public Computer find(long id) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String query = "SELECT * FROM " + TABLE + " WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            Computer computer = null;
            while (rs.next()) {
                computer = mapper.unmap(rs);
            }
            return computer;
        }
    }

    @Override
    public ArrayList<Computer> findAll(int firstRow, int countRow) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String query = "SELECT * "
                    + "FROM " + TABLE
                    +" LIMIT ?,?";
            
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, firstRow);
            stmt.setInt(2, countRow);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Computer> computers = new ArrayList<Computer>();
            while(rs.next()){
                computers.add(mapper.unmap(rs));
            }
            return computers;
        }
    }

    @Override
    public void update(Computer computer) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String query = "UPDATE " + TABLE + "SET name = ?, introduced = ?, discontinued = ?, company_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            mapper.map(computer, stmt);
            stmt.executeUpdate();
        }
    }
}
