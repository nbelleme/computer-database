package com.excilys.persistence;

import com.excilys.model.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICompanyDAO {

    void delete(Company item);

    Company find(Long id);

    List<Company> findAll(int order);

    int getTotal();
}
