package com.excilys.service;

import com.excilys.model.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICompanyService {

    Company add(Company company);

    void delete(Company company);

    Company find(long id);

    List<Company> findAll();

    Company update(Company company);
}
