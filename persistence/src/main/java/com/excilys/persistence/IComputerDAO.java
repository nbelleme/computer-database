package com.excilys.persistence;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by nbelleme on 22/08/16.
 */
public interface IComputerDAO {
    Computer save(Computer computer);

    Page<Computer> findAll(Pageable search);

    List<Computer> findAll();

    void delete(Computer computer);

    Computer find(long id);

    Page<Computer> findByNameOrCompany(String string, Company company, Pageable page);

    List<Computer> findByCompany(Company company);
}
