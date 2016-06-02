package com.excilys.service;

import com.excilys.model.Company;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author nbelleme
 */
@Service
@Scope("singleton")
public class CompanyService {

    @Autowired
    private CompanyDAO companyDAO;

    private Logger logger = LoggerFactory.getLogger(CompanyService.class);

    /**
     * @param company company needed to be added
     * @return inserted company id
     */
    public long add(Company company) {
        try {
            return companyDAO.add(company);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
    }

    /**
     * @param company company need to be deleted
     */
    @Transactional
    public void delete(Company company) {
        if (company != null) {
//      computerDAO.deleteFromCompanyId(company.getId());
            companyDAO.delete(company);
        }

    }

    /**
     * @param id id of the company looked for
     * @return company company found
     */
    public Company find(long id) {
        try {
            return companyDAO.find(id);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
    }

    /**
     * @return List<Computer> list of entity retrieved
     */
    public List<Company> findAll() {
        return companyDAO.findAll(1);
    }

    /**
     * @param company company need to be updated
     */
    public void update(Company company) {
        try {
            companyDAO.update(company);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
    }

}
