package com.excilys.persistence;

import com.excilys.model.Company;
import com.excilys.repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Scope("singleton")
public class CompanyDAO {

    Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

    @Autowired
    private CompanyRepository companyRepository;

    public Company add(Company item){

        return companyRepository.save(item);
    }

    public void delete(Company item) {
        companyRepository.delete(item);
    }

    public Company find(Long id) {
        return companyRepository.findOne(id);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public void update(Company item) {
        logger.debug("CompanyDAO ---- update");
    }
}
