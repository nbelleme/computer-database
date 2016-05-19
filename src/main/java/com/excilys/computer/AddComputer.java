package com.excilys.computer;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.DaoException;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.validator.ComputerValidator;

/**
 * Servlet implementation class addComputer
 */
public class AddComputer extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Autowired
  private ComputerService computerService;
  @Autowired
  private CompanyService companyService;
  @Autowired
  private ComputerValidator computerValidator;

  private Logger logger = LoggerFactory.getLogger(AddComputer.class);

  /**
   * @see HttpServlet#HttpServlet()
   */
  public AddComputer() {
    super();
  }

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
        config.getServletContext());
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    ArrayList<Company> companies = (ArrayList<Company>) companyService.findAll();
    request.setAttribute("companies", companies);
    request.getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String name = request.getParameter("computerName");
    if (name != null) {
      name = name.trim();
    }
    String introduced = request.getParameter("introduced");
    if (introduced != null) {
      introduced = introduced.trim();
    }
    String discontinued = request.getParameter("discontinued");
    if (discontinued != null) {

      discontinued = discontinued.trim();
    }
    String companyId = request.getParameter("companyId");
    if (companyId != null) {
      companyId = companyId.trim();
    }
    PrintWriter out = response.getWriter();
    out.println(introduced);
    LocalDate introducedDateTime = null;
    LocalDate discontinuedDateTime = null;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    if (introduced != "") {
      if (introduced.contains("/")) {
        introduced = introduced.replace("/", "");

      } else {
        introduced = introduced.replace("-", "");
      }
      LocalDate introducedDate = LocalDate.parse(introduced, formatter);
    }
    if (discontinued != "") {
      if (discontinued.contains("/")) {
        discontinued = discontinued.replace("/", "");

      } else {
        discontinued = discontinued.replace("-", "");
      }
      LocalDate discontinuedDate = LocalDate.parse(discontinued, formatter);
    }

    Company company = null;
    long idCompany = -1;

    try {
      idCompany = Long.parseLong(companyId);
    } catch (NumberFormatException e) {

    }

    try {
      company = companyService.find(idCompany);
    } catch (DaoException e) {
      out.print("company error");
    }
    Computer computer = new Computer.Builder().name(name).introduced(introducedDateTime)
        .discontinued(discontinuedDateTime).company(company).build();

    computerValidator.isValid(computer);
    computerService.add(computer);
    request.getRequestDispatcher("/computer/view/all").forward(request, response);
  }

}
