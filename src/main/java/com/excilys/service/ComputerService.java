package com.excilys.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.model.Computer;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.ComputerDAO;
import com.excilys.persistence.DaoException;
import com.excilys.validator.ComputerValidator;

public class ComputerService {
  private ComputerDAO computerDAO;
  private CompanyDAO companyDAO;
  private static ComputerService instance = null;
  private Logger logger = LoggerFactory.getLogger(ComputerService.class);
  private ComputerValidator computerValidator;

  /**
   * Get the instance of the singleton.
   *
   * @return instance instance of the singleton
   */
  public static ComputerService getInstance() {
    if (instance == null) {
      synchronized (ComputerService.class) {
        if (instance == null) {
          instance = new ComputerService();
        }
      }
    }
    return instance;
  }

  /**
   * Default constructor.
   */
  private ComputerService() {
    computerDAO = ComputerDAO.getInstance();
    companyDAO = CompanyDAO.getInstance();
    computerValidator = ComputerValidator.INSTANCE;
  }

  /**
   * @param computer
   *          computer to add
   * @return computer computer added
   * @throws DaoException
   *           DaoException
   */
  public Computer add(Computer computer) throws DaoException {
    try {
      long id = -1;
      if (computer.getIntroduced().isBefore(computer.getDiscontinued())) {
        if (computer.getCompany() != null) {
          if (companyDAO.find(computer.getCompany().getId()) == null) {
            id = computerDAO.add(computer);
          } else {
            throw new DaoException("Company not found");
          }
        } else {
          id = computerDAO.add(computer);
        }
      } else {
        throw new DaoException("Date not valid");
      }
      computer.setId(id);
      return computer;
    } catch (DaoException e) {
      logger.error(e.getMessage());
      throw new DaoException(e);
    }
  }

  /**
   * @param computer
   *          computer needed to be updated
   * @throws DaoException
   *           DaoException
   */
  public void update(Computer computer) {
    computerValidator.isValid(computer);
    computerDAO.update(computer);
  }

  /**
   * @param computer
   *          computer needed to be deleted
   * @throws DaoException
   *           DaoException
   */
  public void delete(Computer computer) throws DaoException {
    try {
      computerDAO.delete(computer);
    } catch (DaoException e) {
      logger.error(e.getMessage());
      throw new DaoException(e);
    }

  }

  /**
   * @param id
   *          id of the entity to find
   * @return Computer computer found
   * @throws DaoException
   *           DaoException
   */
  public Computer find(long id) throws DaoException {
    try {
      return computerDAO.find(id);
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
  public List<Computer> findSeveral(int firstRow, int countRow) {
    return computerDAO.findSeveral(firstRow, countRow);
  }

  public int getTotal() {
    return computerDAO.getTotal();
  }

  public List<Computer> findByNameOrCompany(String name, String orderBy, String orderSort,
      int firstRow, int count) {
    return computerDAO.findByNameOrCompany(name, orderBy, orderSort, firstRow, count);
  }

  public int getNumberEntriesFindByNameOrCompany(String name) {
    return computerDAO.getNumberEntriesFindByNameOrCompany(name);
  }
}
