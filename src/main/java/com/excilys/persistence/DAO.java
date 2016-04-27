package com.excilys.persistence;

import java.util.List;

public interface DAO<T> {

  /**
   * Add item to the DB.
   *
   * @param item
   *          item needed to be added to the DB
   * @return id id of the item added
   * @throws DaoException
   *           DaoException
   */
  long add(T item) throws DaoException;

  /**
   * Delete item from the DB.
   *
   * @param item
   *          item needed deletion in DB
   * @throws DaoException
   *           DaoException
   */
  void delete(T item) throws DaoException;

  /**
   * Update an item in the DB.
   *
   * @param item
   *          item to update DB
   * @throws DaoException
   *           DaoException
   */
  void update(T item) throws DaoException;

  /**
   * Find an item and return it.
   *
   * @param id
   *          id of the item wanted
   * @return T return entity found
   * @throws DaoException
   *           DaoException
   */
  T find(long id) throws DaoException;

  /**
   * Find X computers from firstRow.
   *
   * @param firstRow
   *          first row of the table
   * @param countRow
   *          number of return values wanted
   * @return list of entity
   * @throws DaoException
   *           throws DaoException
   */
  List<T> findSeveral(int firstRow, int countRow) throws DaoException;

  /**
   * Get number total of elements.
   *
   * @return int number of elements in the database
   */
  int getTotal() throws DaoException;
}
