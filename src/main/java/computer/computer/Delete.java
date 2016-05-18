package computer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.model.Computer;
import com.excilys.persistence.DaoException;
import com.excilys.service.ComputerService;

/**
 * Servlet implementation class Delete
 */
public class Delete extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private ComputerService computerService;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public Delete() {
    super();
    computerService = ComputerService.getInstance();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String selection = request.getParameter("selection");
    String[] ids = selection.split(",");
    ArrayList<Computer> computers = new ArrayList<>();
    for (int i = 0; i < ids.length; i++) {
      Pattern patternId = Pattern.compile("^\\d+$");
      long id;
      if (patternId.matcher(ids[i]).matches()) {
        id = Long.parseLong(ids[i]);
        computers.add(computerService.find(id));
      }
    }

    for (Computer computer : computers) {
      computerService.delete(computer);
      // computerService.add(computer);
    }
    response.sendRedirect("/ComputerDatabase/computer/view/all");
  }
}
