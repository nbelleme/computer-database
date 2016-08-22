package com.excilys.model;

import java.time.LocalDate;

/**
 * Created by nbelleme on 22/08/16.
 */
public interface IComputer {
    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    LocalDate getIntroduced();

    void setIntroduced(LocalDate introduced);

    LocalDate getDiscontinued();

    void setDiscontinued(LocalDate discontinued);

    Company getCompany();

    void setCompany(Company company);

    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);

    @Override
    String toString();
}
