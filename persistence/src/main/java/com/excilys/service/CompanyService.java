package com.excilys.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.model.Company;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.DaoException;
import com.excilys.repository.CompanyRepository;

/**
 * @author nbelleme
 */
@Service
@Scope("singleton")
public class CompanyService {

  @Autowired
  private CompanyDAO companyDAO;
//  @Autowired
//  private ComputerDAO computerDAO;

  @Autowired
  private CompanyRepository companyManager;

  private Logger logger = LoggerFactory.getLogger(CompanyService.class);

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
   */
  @Transactional
  public void delete(Company company) {
    if (company != null) {
//      computerDAO.deleteFromCompanyId(company.getId());
      companyDAO.delete(company);
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
    return companyManager.findAll();
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
