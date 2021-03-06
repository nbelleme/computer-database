package com.excilys.service.impl;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.impl.ComputerDAO;
import com.excilys.repository.ComputerRepository;
import com.excilys.service.IComputerService;
import com.excilys.validator.ComputerValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerService implements IComputerService {
    private Logger logger = LoggerFactory.getLogger(ComputerService.class);

    @Autowired
    private ComputerValidator computerValidator;
    @Autowired
    private ComputerDAO computerDAO;
    @Autowired
    private ComputerRepository computerManager;

    @Override
    public Computer save(Computer computer) {
        return computerManager.save(computer);
    }

    @Override
    public void delete(Computer computer) {
        computerValidator.assertValid(computer);
        computerDAO.delete(computer);
    }

    @Override
    public void update(Computer computer) {
        computerValidator.assertValid(computer);
        computerManager.save(computer);
    }

    @Override
    public void deleteMultiple(List<Computer> computers) {
        for (Computer computer : computers) {
            delete(computer);
        }
    }

    @Override
    public Computer find(Long id) {
        return computerDAO.find(id);
    }

    @Override
    public Page<Computer> findAll(Pageable search) {
        return computerDAO.findAll(search);
    }

    @Override
    public Page<Computer> findByNameOrCompany(String string, Company company, Pageable page) {
        if (company.getName() == null) {
            company.setName("");
        }
        return computerDAO.findByNameOrCompany(string, company, page);
    }

    @Override
    public List<Computer> findByCompany(Company company) {
        return computerDAO.findByCompany(company);
    }

    @Override
    public List<Computer> findAll(){
        return computerDAO.findAll();
    }
}
