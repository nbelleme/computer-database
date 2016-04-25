package com.excilys.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final static String URL = "jdbc:mysql://localhost/";
    private final static String USER = "admincdb";
    private final static String PASSWORD = "qwerty1234";
    private final static String DATABASE = "computer-database-db";
    private final static String OPTIONS = "?zeroDateTimeBehavior=convertToNull";

    private static Database _instance = null;

    public static synchronized Database getInstance() {
        if (_instance == null) {
            synchronized (Database.class) {
                if (_instance == null) {
                    _instance = new Database();
                }
            }
        }
        return _instance;
    }

    public static Connection getConnection() throws DatabaseException {
        try {
            return DriverManager.getConnection(URL + DATABASE + OPTIONS, USER, PASSWORD);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

    }
}
