package com.excilys.service.impl;

import com.excilys.model.Company;
import com.excilys.persistence.DaoException;
import com.excilys.persistence.ICompanyDAO;
import com.excilys.persistence.impl.CompanyDAO;
import com.excilys.service.ICompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyService implements ICompanyService {

    @Autowired
    private ICompanyDAO companyDAO;

    private Logger logger = LoggerFactory.getLogger(CompanyService.class);

    @Override
    public Company add(Company company) {
        return companyDAO.add(company);
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
        return companyDAO.findAll();
    }

    @Override
    public Company update(Company company) {
        try {
            return companyDAO.update(company);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
    }

}
