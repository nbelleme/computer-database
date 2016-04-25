package com.excilys.persistence;

public class DatabaseException extends RuntimeException {
    public DatabaseException(Exception e) {
        super(e);
    }
}
