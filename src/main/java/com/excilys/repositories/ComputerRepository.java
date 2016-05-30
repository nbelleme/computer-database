package com.excilys.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.model.Company;
import com.excilys.model.Computer;

public interface ComputerRepository extends JpaRepository<Computer, Long> {

  Page<Computer> findByName(String name);

  Page<Computer> findByNameStartingWith(String name);

  Page<Computer> findByCompany(Company company);

}
