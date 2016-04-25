package com.excilys.persistence;

public class DaoException extends RuntimeException {

    public DaoException(Exception e) {
        super(e);
    }
}
