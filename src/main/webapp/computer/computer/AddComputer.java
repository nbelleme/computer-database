package computer;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
  private CompanyService companyService;
  private ComputerValidator computerValidator;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public AddComputer() {
    super();
    companyService = CompanyService.getInstance();
    computerValidator = ComputerValidator.INSTANCE;
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
    name = name.trim();
    String introduced = request.getParameter("introduced");
    introduced = introduced.trim();
    String discontinued = request.getParameter("discontinued");
    discontinued = discontinued.trim();
    String companyId = request.getParameter("companyId");
    companyId = companyId.trim();
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

    ComputerService computerService = ComputerService.getInstance();
    CompanyService companyService = CompanyService.getInstance();
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
