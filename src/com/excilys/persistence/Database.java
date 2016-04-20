package com.excilys.persistence;

import java.sql.*;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;

import com.sun.rowset.CachedRowSetImpl;
import com.sun.rowset.JdbcRowSetImpl;

public class Database {
	private final static String URL = "jdbc:mysql://localhost/";
	private final static String USER = "admincdb";
	private final static String PASSWORD = "qwerty1234";
	private final static String DATABASE = "computer-database-db";
	private final static String OPTIONS = "?zeroDateTimeBehavior=convertToNull";

	public static Connection getConnection() throws SQLException{
	    return DriverManager.getConnection(URL+DATABASE+OPTIONS, USER, PASSWORD);
	    
	}
}
