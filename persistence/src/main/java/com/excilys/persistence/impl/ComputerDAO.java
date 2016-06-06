package com.excilys.persistence.impl;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.repository.ComputerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Scope("singleton")
public class ComputerDAO {

    @Autowired
    ComputerRepository computerManager;
    private Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

    public Computer save(Computer computer) {
        logger.info("ComputerDAO ---- save");
        return computerManager.save(computer);

    }

    public Page<Computer> findAll(Pageable search) {
        logger.info("ComputerDAO ---- findAll");
        return computerManager.findAll(search);
    }

    public List<Computer> findAll(){
        return computerManager.findAll();
    }

    public void delete(Computer computer) {
        logger.info("ComputerDAO ---- delete");
        computerManager.delete(computer);
    }

    public Computer find(long id) {
        logger.info("ComputerDAO ---- find");
        return computerManager.findOne(id);
    }

    public Page<Computer> findByNameOrCompany(String string, Company company, Pageable page) {
        return computerManager.findByNameStartingWithOrCompanyNameStartingWith(string,
                company.getName(), page);
    }

    public List<Computer> findByCompany(Company company) {
        return computerManager.findByCompany(company);
    }
}
