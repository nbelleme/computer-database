package computer;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.validator.ComputerValidator;
import com.excilys.validator.ValidatorException;

import dto.ComputerDTO;

/**
 * Servlet implementation class ComputerServletEdit
 */

public class Edit extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private ComputerService computerService;
  private CompanyService companyService;
  private ComputerValidator computerValidator;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public Edit() {
    super();
    computerService = ComputerService.getInstance();
    companyService = CompanyService.getInstance();
    computerValidator = ComputerValidator.INSTANCE;
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    String idParam = request.getParameter("id");
    Pattern patternId = Pattern.compile("^\\d+$");
    long id;
    if (patternId.matcher(idParam).matches()) {
      id = Integer.parseInt(idParam);
    } else {
      throw new ValidatorException("id illegal");
    }

    Computer computer = computerService.find(id);
    ComputerDTOMapper computerDtoMapper = ComputerDTOMapper.INSTANCE;
    ComputerDTO computerDTO = computerDtoMapper.map(computer);

    List<Company> companies = companyService.findAll();
    request.setAttribute("companies", companies);

    request.setAttribute("computer", computerDTO);
    request.getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    PrintWriter out = response.getWriter();

    String id = request.getParameter("id").trim();
    String name = request.getParameter("computerName").trim();
    String introduced = request.getParameter("introduced").trim();
    String discontinued = request.getParameter("discontinued").trim();
    String companyId = request.getParameter("companyId").trim();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    ComputerDTO computerDTO = new ComputerDTO(id, name, introduced, discontinued, companyId, "");
    Computer computer = ComputerDTOMapper.INSTANCE.unmap(computerDTO);
    out.println(computer.toString());

    computerValidator.isValid(computer);
    computerService.update(computer);

    request.getRequestDispatcher("/computer/view/all").forward(request, response);
  }

}
