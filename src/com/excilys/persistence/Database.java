package com.excilys.persistence;

import java.sql.*;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;

import com.sun.rowset.CachedRowSetImpl;
import com.sun.rowset.JdbcRowSetImpl;

public class Database {
	private JdbcRowSet rowset;
	private static String url = "jdbc:mysql://localhost/";
	private static String username = "admincdb";
	private static String password = "qwerty1234";
	private static String database = "computer-database-db";

	/*
	 * Private class that will be instantiate at first call of getInstance
	 */
	private static class DatabaseHolder {
		private final static Database singletonDatabase = new Database();
	}

	public static Database getInstance() {
		return DatabaseHolder.singletonDatabase;
	}

	public JdbcRowSet getRowSet() throws SQLException {
		rowset = new JdbcRowSetImpl();
		rowset.setUrl(url + database);
		rowset.setUsername(username);
		rowset.setPassword(password);
		return rowset;
	}

	public void close() throws SQLException {
		rowset.close();
	}
}
