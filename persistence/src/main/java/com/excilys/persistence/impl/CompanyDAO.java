package com.excilys.persistence.impl;

import com.excilys.model.Company;
import com.excilys.persistence.DaoException;
import com.excilys.persistence.ICompanyDAO;
import com.excilys.repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.config.XmlRepositoryConfigurationSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyDAO implements ICompanyDAO{
    Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

    @Autowired
    private CompanyRepository companyRepository;

    public Company add(Company company) throws DaoException {
        return companyRepository.save(company);
    }

    public void delete(Company company) {
        companyRepository.delete(company);
    }

    public Company find(Long id) {
        return companyRepository.findOne(id);
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public int getTotal() {
        return 0;
    }

    public Company update(Company company) {
        logger.debug("CompanyDAO ---- update");
        return companyRepository.save(company);
    }

}
