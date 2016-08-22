package com.excilys.model;

/**
 * Created by nbelleme on 22/08/16.
 */
public interface ICompany {
    @Override
    boolean equals(Object obj);

    Long getId();

    String getName();

    @Override
    int hashCode();

    void setId(Long id);

    void setName(String name);

    @Override
    String toString();
}
