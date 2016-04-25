package com.excilys.persistence;

import java.util.List;

public interface DAO<T> {

    /**
     * Add item to the DB
     * 
     * @param item
     */
    public long add(T item) throws DaoException;

    /**
     * Add item from the DB
     * 
     * @param item
     */
    public void delete(T item) throws DaoException;

    /**
     * Update an item in the DB
     * 
     * @param item
     */
    public void update(T item) throws DaoException;

    /**
     * Find an item and return it
     * 
     * @param item
     * @return T
     */
    public T find(long id) throws DaoException;

    public List<T> findAll(int firstRow, int countRow) throws DaoException;
}
