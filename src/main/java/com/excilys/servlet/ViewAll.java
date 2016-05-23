package com.excilys.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.model.Computer;
import com.excilys.persistence.SearchComputer;
import com.excilys.service.ComputerService;
import com.excilys.ui.Page;

/**
 * Servlet implementation class ComputerServletViewAll
 */
public class ViewAll extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Autowired
  private ComputerService computerService;
  @Autowired
  private ComputerDTOMapper computerDtoMapper;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
    SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
        config.getServletContext());
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Page<ComputerDTO> page = new Page.Builder<ComputerDTO>().build();

    SearchComputer search = new SearchComputer(request);
    search.setPage(page);

    if (search.getOrderBy() != null) {
      request.setAttribute("orderBy", search.getOrderBy().getName());
    }

    if (search.getName() != null) {
      request.setAttribute("search", search.getName());
    }

    if (search.getOrderSort() != null && search.getOrderSort() != "") {
      request.setAttribute("orderSort", search.getOrderSort());
    }

    int nbElementTotal = computerService.getNumberFindBySearch(search);
    int nbElementPage = getNbElementPage(request.getParameter("nbElementPage"));
    int nbPageTotal = (int) Math.ceil((double) nbElementTotal / nbElementPage);
    int nbCurrentPage = getPageNumber(request.getParameter("page"), nbPageTotal);

    page.setNbElementTotal(nbElementTotal);
    page.setNbPageTotal(nbPageTotal);
    page.setNbCurrentPage(nbCurrentPage);

    List<Computer> computers = new ArrayList<Computer>();
    computers = computerService.findBySearch(search);

    List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
    for (Computer computer : computers) {
      computersDTO.add(computerDtoMapper.map(computer));
    }

    page.setElements(computersDTO);
    request.setAttribute("page", page);
    request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

  private int getNbElementPage(String param) {
    int nbElementPage = 10;
    if (param != null) {
      String paramNbElementPage = param;
      Pattern patternElementPage = Pattern.compile("^(?!0+$)\\d+$");
      if (patternElementPage.matcher(paramNbElementPage).matches()) {
        nbElementPage = Integer.parseInt(paramNbElementPage);
      }
    }
    return nbElementPage;
  }

  private int getPageNumber(String param, int nbPageTotal) {
    int nbCurrentPage = 1;
    if (param != null) {
      nbCurrentPage = Integer.parseInt(param);
      if (nbCurrentPage > nbPageTotal) {
        nbCurrentPage = nbPageTotal;
      }
    }
    return nbCurrentPage;
  }

}