package com.excilys.service;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IComputerService {

    Computer save(Computer computer);

    void update(Computer computer);

    void delete(Computer computer);

    void deleteMultiple(List<Computer> computers);

    Computer find(Long id);

    List<Computer> findAll();

    Page<Computer> findAll(Pageable search);

    Page<Computer> findByNameOrCompany(String string, Company company, Pageable page);

    List<Computer> findByCompany(Company company);
}
