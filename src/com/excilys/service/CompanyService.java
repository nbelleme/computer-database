package com.excilys.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.model.Company;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.DaoException;

public class CompanyService {

    private CompanyDAO companyDAO;
    private Logger logger = LoggerFactory.getLogger(CompanyService.class);

    private static CompanyService _instance;

    public CompanyService() {
        companyDAO = CompanyDAO.getInstance();
    }

    public static CompanyService getInstance() {
        if (_instance == null) {
            synchronized (CompanyService.class) {
                if (_instance == null) {
                    _instance = new CompanyService();
                }
            }
        }

        return _instance;
    }

    public long add(Company company) throws DaoException {
        try {
            return companyDAO.add(company);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
    }

    public void delete(Company company) throws DaoException {
        try {
            companyDAO.delete(company);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
    }

    public Company find(long id) throws DaoException {
        try {
            return companyDAO.find(id);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
    }

    public List<Company> findAll(int firstRow, int countRow) throws DaoException {
        try {
            return companyDAO.findAll(firstRow, countRow);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
    }

    public void update(Company company) throws DaoException {
        try {
            companyDAO.update(company);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
    }

}
