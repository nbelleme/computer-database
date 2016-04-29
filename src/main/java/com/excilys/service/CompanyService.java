package com.excilys.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.model.Company;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.DaoException;

/**
 * @author nbelleme
 */
public class CompanyService {

  private CompanyDAO companyDAO;
  private Logger logger = LoggerFactory.getLogger(CompanyService.class);

  private static CompanyService instance;

  /**
   * Default constructor.
   */
  private CompanyService() {
    companyDAO = CompanyDAO.getInstance();
  }

  /**
   * @return instance of CompanyService
   */
  public static CompanyService getInstance() {
    if (instance == null) {
      synchronized (CompanyService.class) {
        if (instance == null) {
          instance = new CompanyService();
        }
      }
    }

    return instance;
  }

  /**
   * @param company
   *          company needed to be added
   * @return inserted company id
   * @throws DaoException
   *           DaoException
   */
  public long add(Company company) throws DaoException {
    try {
      return companyDAO.add(company);
    } catch (DaoException e) {
      logger.error(e.getMessage());
      throw new DaoException(e);
    }
  }

  /**
   * @param company
   *          company need to be deleted
   * @throws DaoException
   *           DaoException
   */
  public void delete(Company company) throws DaoException {
    try {
      companyDAO.delete(company);
    } catch (DaoException e) {
      logger.error(e.getMessage());
      throw new DaoException(e);
    }
  }

  /**
   * @param id
   *          id of the company looked for
   * @return company company found
   * @throws DaoException
   *           DaoException
   */
  public Company find(long id) throws DaoException {
    try {
      return companyDAO.find(id);
    } catch (DaoException e) {
      logger.error(e.getMessage());
      throw new DaoException(e);
    }
  }

  /**
   * @param firstRow
   *          firstRow to look for
   * @param countRow
   *          number of value wanted
   * @return List<Computer> list of entity retrieved
   * @throws DaoException
   *           DaoException
   */
  public List<Company> findAll() throws DaoException {
    try {
      return companyDAO.findAll();
    } catch (DaoException e) {
      logger.error(e.getMessage());
      throw new DaoException(e);
    }
  }

  public List<Company> findSeveral(int firstRow, int countRow) throws DaoException {
    try {
      return companyDAO.findSeveral(firstRow, countRow);
    } catch (DaoException e) {
      logger.error(e.getMessage());
      throw new DaoException(e);
    }
  }

  /**
   * @param company
   *          company need to be updated
   * @throws DaoException
   *           DaoException
   */
  public void update(Company company) throws DaoException {
    try {
      companyDAO.update(company);
    } catch (DaoException e) {
      logger.error(e.getMessage());
      throw new DaoException(e);
    }
  }

}
