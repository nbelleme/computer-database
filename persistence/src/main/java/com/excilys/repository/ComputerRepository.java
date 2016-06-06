package com.excilys.repository;

import com.excilys.model.Computer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerRepository extends JpaRepository<Computer, Long> {

    Page<Computer> findByNameStartingWithOrCompanyNameStartingWith(String name, String companyName,
                                                                   Pageable page);

}
