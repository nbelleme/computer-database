package computer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.model.Computer;
import com.excilys.persistence.DaoException;
import com.excilys.service.ComputerService;

/**
 * Servlet implementation class CompanyServlet
 */
public class CompanyServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public CompanyServlet() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    ComputerService computerService = ComputerService.getInstance();
    Computer computer;
    String pathInfo = request.getPathInfo();
    String[] pathParts = pathInfo.split("/");
    String part1 = pathParts[1];
    long id = Long.parseLong(part1);
    request.setAttribute("id", id);
    try {
      computer = computerService.find(574L);
      request.setAttribute("computer", computer);
      request.getRequestDispatcher("/NewFile.jsp").forward(request, response);
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
    doGet(request, response);
  }

}