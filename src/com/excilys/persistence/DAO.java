package com.excilys.persistence;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

	/**
	 * Add item to the DB
	 * 
	 * @param item
	 */
	public void add(T item) throws SQLException;

	/**
	 * Add item from the DB
	 * 
	 * @param item
	 */
	public void delete(T item) throws SQLException;

	/**
	 * Update an item in the DB
	 * 
	 * @param item
	 */
	public void update(T item) throws SQLException;

	/**
	 * Find an item and return it
	 * 
	 * @param item
	 * @return T
	 */
	public T find(T entity) throws SQLException;
	
	public List<T> findAll() throws SQLException;
}