package com.excilys.service;

import com.excilys.model.Company;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyService implements ICompanyService {

    @Autowired
    private CompanyDAO companyDAO;

    private Logger logger = LoggerFactory.getLogger(CompanyService.class);

    @Override
    public long add(Company company) {
        try {
            return companyDAO.add(company);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    @Transactional
    public void delete(Company company) {
        //FIXME
//      computerDAO.deleteFromCompanyId(company.getId());
        companyDAO.delete(company);
    }

    @Override
    public Company find(long id) {
        try {
            return companyDAO.find(id);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public List<Company> findAll() {
        return companyDAO.findAll(1);
    }

    @Override
    public void update(Company company) {
        try {
            companyDAO.update(company);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
    }

}
