package com.excilys.persistence.impl;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.IComputerDAO;
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
public class ComputerDAO implements IComputerDAO {

    @Autowired
    ComputerRepository computerManager;
    private Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

    @Override
    public Computer save(Computer computer) {
        logger.info("ComputerDAO ---- save");
        return computerManager.save(computer);

    }

    @Override
    public Page<Computer> findAll(Pageable search) {
        logger.info("ComputerDAO ---- findAll");
        return computerManager.findAll(search);
    }

    @Override
    public List<Computer> findAll(){
        return computerManager.findAll();
    }

    @Override
    public void delete(Computer computer) {
        logger.info("ComputerDAO ---- delete");
        computerManager.delete(computer);
    }

    @Override
    public Computer find(long id) {
        logger.info("ComputerDAO ---- find");
        return computerManager.findOne(id);
    }

    @Override
    public Page<Computer> findByNameOrCompany(String string, Company company, Pageable page) {
        return computerManager.findByNameStartingWithOrCompanyNameStartingWith(string,
                company.getName(), page);
    }

    @Override
    public List<Computer> findByCompany(Company company) {
        return computerManager.findByCompany(company);
    }
}
