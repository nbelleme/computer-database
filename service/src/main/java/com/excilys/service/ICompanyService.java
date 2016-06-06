package com.excilys.service;

import com.excilys.model.Company;

import java.util.List;

public interface ICompanyService {

    long add(Company company);

    void delete(Company company);

    Company find(long id);

    List<Company> findAll();

    void update(Company company);
}
