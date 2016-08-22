package com.excilys.persistence;

import com.excilys.model.Company;

import java.util.List;

public interface ICompanyDAO {

    void delete(Company item);

    Company find(Long id);

    List<Company> findAll();

    int getTotal();

    Company add(Company company);

    Company update(Company company);
}
