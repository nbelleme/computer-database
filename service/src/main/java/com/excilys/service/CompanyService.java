package com.excilys.service;

import com.excilys.model.Company;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    public Company add(Company company) {
        return companyDAO.add(company);
    }

    /**
     * @param company company need to be deleted
     */
    @Transactional
    public void delete(Company company) {
        companyDAO.delete(company);
    }

    /**
     * @return List<Computer> list of entity retrieved
     */
    public List<Company> findAll() {
        return companyDAO.findAll();
    }
}
