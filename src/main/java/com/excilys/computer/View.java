package com.excilys.computer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.model.Computer;
import com.excilys.persistence.DaoException;
import com.excilys.service.ComputerService;

/**
 * Servlet implementation class CompanyServlet
 */
public class View extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Autowired
  private ComputerService computerService;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public View() {
    super();

  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String pathInfo = request.getPathInfo();
    String[] pathParts = pathInfo.split("/");
    String part1 = pathParts[1];

    long id = Long.parseLong(part1);

    try {
      Computer computer = computerService.find(id);
      request.setAttribute("computer", computer);
      request.getRequestDispatcher("/WEB-INF/views/viewComputer.jsp").forward(request, response);
    } catch (DaoException e) {
      request.getRequestDispatcher("/WEB-INF/views/404.html").forward(request, response);
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
