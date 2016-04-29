package computer;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

  /**
   * @see HttpServlet#HttpServlet()
   */
  public AddComputer() {
    super();
    companyService = CompanyService.getInstance();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      ArrayList<Company> companies = (ArrayList<Company>) companyService.findAll();
      request.setAttribute("companies", companies);
      request.getRequestDispatcher("/views/addComputer.jsp").forward(request, response);
    } catch (DaoException e) {
      e.printStackTrace();
      request.getRequestDispatcher("/views/500.html").forward(request, response);
    }
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
    LocalDateTime introducedDateTime = null;
    LocalDateTime discontinuedDateTime = null;
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

      if (introduced != "") {
        if (introduced.contains("/")) {
          introduced = introduced.replace("/", "");

        } else {
          introduced = introduced.replace("-", "");
        }
        LocalDate introducedDate = LocalDate.parse(introduced, formatter);
        introducedDateTime = introducedDate.atStartOfDay();
      }
      if (discontinued != "") {
        if (discontinued.contains("/")) {
          discontinued = discontinued.replace("/", "");

        } else {
          discontinued = discontinued.replace("-", "");
        }
        LocalDate discontinuedDate = LocalDate.parse(discontinued, formatter);
        discontinuedDateTime = discontinuedDate.atStartOfDay();
      }
    } catch (DateTimeException e) {
      request.setAttribute("errorDate", true);
      request.getRequestDispatcher("/views/500.html").forward(request, response);
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

    if (ComputerValidator.INSTANCE.isValid(computer)) {
      try {
        computerService.add(computer);

      } catch (DaoException e) {
        out.print(computer.toString());
        e.printStackTrace();
      }
    } else {
      out.println("INVALID MOTHERFUCKER");
    }

  }

}
