package com.excilys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.model.Company;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findByName(String search);

}
