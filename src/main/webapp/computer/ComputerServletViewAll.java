package computer;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.model.Computer;
import com.excilys.persistence.DaoException;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

/**
 * Servlet implementation class ComputerServletViewAll
 */

public class ComputerServletViewAll extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private ComputerService computerService;
  private CompanyService companyService;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public ComputerServletViewAll() {
    super();
    computerService = ComputerService.getInstance();
    companyService = CompanyService.getInstance();

  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // String pathInfo = request.getPathInfo();
    // String[] pathParts = pathInfo.split("/");
    // int numberRow = 10;
    // if (pathParts[1] != null) {
    // String part1 = pathParts[1];
    // numberRow = Integer.parseInt(part1);
    // }
    try {
      ArrayList<Computer> computers = (ArrayList<Computer>) computerService.findAll(0, 10);
      request.setAttribute("computers", computers);
      request.getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
    } catch (DaoException e) {
      e.printStackTrace();
    }
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
