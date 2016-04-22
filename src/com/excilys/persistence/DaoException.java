package com.excilys.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.main.Main;

public class DaoException extends RuntimeException {

    public DaoException(Exception e) {
        super(e);
    }
}
