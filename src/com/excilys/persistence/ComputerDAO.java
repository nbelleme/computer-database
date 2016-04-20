package com.excilys.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.model.Computer;

import mapper.ComputerMapper;

public class ComputerDAO implements DAO<Computer> {
    private static final String TABLE = "computer";
    private ComputerMapper mapper;

    public ComputerDAO() throws SQLException {
        mapper = ComputerMapper.getMapper();
    }

    @Override
    public void add(Computer computer) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String query = "INSERT INTO " + TABLE + " (name, introduced, discontinued, company_id)"
                    + " VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            mapper.map(computer, stmt);
            stmt.executeUpdate();
        }
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
    public Computer find(Computer entity) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String query = "SELECT * FROM " + TABLE + " WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, entity.getId());
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
