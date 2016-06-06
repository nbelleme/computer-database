package com.excilys.service;

import com.excilys.model.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICompanyService {

    long add(Company company);

    void delete(Company company);

    Company find(long id);

    List<Company> findAll();

    void update(Company company);
}
