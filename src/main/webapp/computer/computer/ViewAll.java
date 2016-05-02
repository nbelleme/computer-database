package computer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.model.Computer;
import com.excilys.persistence.DaoException;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.ui.Page;

/**
 * Servlet implementation class ComputerServletViewAll
 */

public class ViewAll extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private ComputerService computerService;
  private CompanyService companyService;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public ViewAll() {
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
    try {
      Page<Computer> page = new Page.Builder<Computer>().build();

      int nbElementTotal = computerService.getTotal();
      int nbElementPage = page.getNbElementPage();
      int nbCurrentPage = 1;
      int nbPageTotal = 0;

      try {
        if (request.getParameter("nbElementPage") != null) {
          String paramNbElementPage = request.getParameter("nbElementPage");
          Pattern patternElementPage = Pattern.compile("^(?!0+$)\\d+$");
          if (patternElementPage.matcher(paramNbElementPage).matches()) {
            nbElementPage = Integer.parseInt(paramNbElementPage);
          } else {
            nbElementPage = 10;
          }
        }
        nbPageTotal = (int) Math.ceil((double) nbElementTotal / (double) nbElementPage);
        if (request.getParameter("page") != null) {
          String paramNbCurrentPage = request.getParameter("page");
          nbCurrentPage = Integer.parseInt(paramNbCurrentPage);
          if (nbCurrentPage > nbPageTotal) {
            nbCurrentPage = nbPageTotal;
          }
        }

      } catch (NumberFormatException e) {
        request.getRequestDispatcher("/WEB-INF/views/500.html").forward(request, response);
      }

      int firstRow = nbElementPage * (nbCurrentPage - 1);

      page.setNbElementTotal(nbElementTotal);
      page.setNbPageTotal(nbPageTotal);
      page.setElements((ArrayList<Computer>) computerService.findSeveral(firstRow, nbElementPage));
      page.setNbCurrentPage(nbCurrentPage);

      request.setAttribute("page", page);
      request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
    } catch (

    DaoException e) {
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