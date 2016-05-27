package com.excilys.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.ComputerDAO;
import com.excilys.persistence.DaoException;
import com.excilys.persistence.SearchComputer;
import com.excilys.validator.ComputerValidator;

@Service
public class ComputerService {
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

  public void deleteMultiple(List<Computer> computers) {
    for (Computer computer : computers) {
      delete(computer);
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
      logger.debug(e.getMessage());
      throw new DaoException(e);
    }
  }

  public long getTotal() {
    return computerDAO.getTotal();
  }

  public List<Computer> findBySearch(SearchComputer search) {
    logger.debug("ComputerService --- findBySearch");
    int valueOrder = search.getSort().toLowerCase().equals("asc") ? 1 : -1;
    List<Company> companies = companyDAO.findByName(search.getNameToSearch(), valueOrder);
    search.setCompanyIds(companies);
    List<Computer> computers = computerDAO.findBySearch(search);

    for (Computer computer : computers) {
      if (computer.getCompany() != null) {
        Company company = companyDAO.find(computer.getCompany().getId());
        computer.setCompany(company);
      }
    }

    return computers;
  }

  public long getNumberFindBySearch(SearchComputer search) {
    return computerDAO.getNumberFindBySearch(search);
  }

}
