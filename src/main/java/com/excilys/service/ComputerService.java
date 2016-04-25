package com.excilys.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.model.Computer;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.ComputerDAO;
import com.excilys.persistence.DaoException;

public class ComputerService {
    private ComputerDAO computerDAO;
    private CompanyDAO companyDAO;
    private static ComputerService _instance = null;
    private Logger logger = LoggerFactory.getLogger(ComputerService.class);

    public static ComputerService getInstance() {
        if (_instance == null) {
            synchronized (ComputerService.class) {
                if (_instance == null) {
                    _instance = new ComputerService();
                }
            }
        }
        return _instance;
    }

    public ComputerService() {
        computerDAO = ComputerDAO.getInstance();
        companyDAO = CompanyDAO.getInstance();
    }

    public Computer add(Computer computer) throws DaoException {
        try {
            if (computer.getIntroduced().isAfter(computer.getDiscontinued())) {
                if (computer.getCompany() != null) {
                    if (companyDAO.find(computer.getCompany().getId()) == null) {
                        computer.setId(computerDAO.add(computer));
                    }
                } else {
                    computer.setId(computerDAO.add(computer));
                }
            } else {
                return null;
            }
            return computer;
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
    }

    public void update(Computer computer) throws DaoException {
        try {
            if (computer.getIntroduced().isAfter(computer.getDiscontinued())) {
                if (computer.getCompany() != null) {
                    if (companyDAO.find(computer.getCompany().getId()) == null) {
                        computerDAO.update(computer);
                    }
                } else {
                    computerDAO.update(computer);
                }
            }
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
    }

    public void delete(Computer computer) throws DaoException {
        try {
            computerDAO.delete(computer);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }

    }

    public Computer find(long id) throws DaoException {
        try {
            return computerDAO.find(id);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
    }

    public List<Computer> findAll(int firstRow, int countRow) throws DaoException {
        try {
            return computerDAO.findAll(firstRow, countRow);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
    }
}
