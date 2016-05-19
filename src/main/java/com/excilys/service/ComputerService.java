package com.excilys.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.excilys.model.Computer;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.ComputerDAO;
import com.excilys.persistence.DaoException;
import com.excilys.persistence.SearchComputer;
import com.excilys.validator.ComputerValidator;

@Service
@Scope("singleton")
public class ComputerService {
  private static ComputerService instance = null;
  private Logger logger = LoggerFactory.getLogger(ComputerService.class);

  @Autowired
  private ComputerValidator computerValidator;
  @Autowired
  private ComputerDAO computerDAO;
  @Autowired
  private CompanyDAO companyDAO;

  /**
   * @param computer
   *          computer to add
   * @return computer computer added
   * @throws DaoException
   *           DaoException
   */
  public Computer add(Computer computer) throws DaoException {
    long id = computerDAO.add(computer);
    computer.setId(id);
    return computer;
  }

  /**
   * @param computer
   *          computer needed to be updated
   * @throws DaoException
   *           DaoException
   */
  public void update(Computer computer) {
    computerValidator.isIdValid(computer.getId());
    computerValidator.isValid(computer);
    computerDAO.update(computer);
  }

  /**
   * @param computer
   *          computer needed to be deleted
   * @throws DaoException
   *           DaoException
   */
  public void delete(Computer computer) {
    computerValidator.isIdValid(computer.getId());
    computerValidator.isValid(computer);
    computerDAO.delete(computer);
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

  public List<Computer> findBySearch(SearchComputer search) {
    return computerDAO.findBySearch(search);
  }

  public int getNumberFindBySearch(SearchComputer search) {
    return computerDAO.getNumberFindBySearch(search);
  }
}
