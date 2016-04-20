package com.excilys.persistence;

import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.rowset.JdbcRowSet;

import com.excilys.model.Computer;

import mapper.ComputerMapper;

public class ComputerDAO implements DAO<Computer> {
    private static final String TABLE = "computer";
    private ComputerMapper mapper;

    public ComputerDAO() {

        mapper = new ComputerMapper();
    }

    @Override
    public void add(Computer computer) throws SQLException {
        try (JdbcRowSet rowset = Database.getInstance().getRowSet()) {
            rowset.setCommand("SELECT * FROM " + TABLE);
            rowset.execute();
            rowset.moveToInsertRow();
            mapper.map(computer, rowset);
            rowset.insertRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Computer computer) throws SQLException {
        try (JdbcRowSet rowset = Database.getInstance().getRowSet()) {
            rowset.setCommand("SELECT * FROM " + TABLE + " WHERE id = ?");
            rowset.setLong(1, computer.getId());
            rowset.execute();
            rowset.last();
            rowset.deleteRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Computer find(Long id) throws SQLException {
        JdbcRowSet rowset = Database.getInstance().getRowSet();
        rowset.setCommand("SELECT * FROM " + TABLE + " WHERE id = ?");
        rowset.setLong(1, id);
        rowset.execute();
        Computer computer = mapper.unmap(rowset);
        return computer;
    }

    @Override
    public void update(Computer computer) throws SQLException {

        JdbcRowSet rowset = Database.getInstance().getRowSet();
        rowset.setCommand("SELECT * FROM computer WHERE id = ? ");
        rowset.setLong(1, computer.getId());
        rowset.execute();

        mapper.map(computer, rowset);
        rowset.updateRow();
    }
}
