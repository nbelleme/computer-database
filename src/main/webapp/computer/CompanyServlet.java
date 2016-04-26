package computer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.model.Computer;
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
    Computer computer = computerService.find(574L);
    // ArrayList<Computer> computers = (ArrayList) computerService.findAll(0, 15);

    // Computer computer = new Computer.Builder().name("bonjour").build();
    request.setAttribute("computer", computer);
    request.getRequestDispatcher("/NewFile.jsp").forward(request, response);
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
