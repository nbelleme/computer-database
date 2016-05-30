package com.excilys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.DaoException;
import com.excilys.persistence.SearchComputer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import com.excilys.ui.Page;
import com.excilys.util.Parser;
import com.excilys.validator.ComputerValidator;

@Controller
@RequestMapping("/computer")
public class ComputerController {

  private static final String ORDER_BY_KEY = "orderBy";
  private static final String SORT_KEY = "orderSort";
  private static final String PAGE_KEY = "page";
  private static final String NB_ELEMENT_PAGE_KEY = "nbElementPage";

  private static final String ID_KEY = "id";
  private static final String NAME_KEY = "name";
  private static final String INTRODUCED_KEY = "introduced";
  private static final String DISCONTINUED_KEY = "discontinued";
  private static final String COMPANY_KEY = "company";

  private static final String SELECTION_KEY = "selection";

  private static final String DASHBOARD_JSP = "dashboard";
  private static final String ADD_JSP = "/computer/add";
  private static final String EDIT_JSP = "/computer/edit";

  private static final String PATH_DASHBOARD = "/computer/view/all";

  @Autowired
  private ComputerService computerService;

  @Autowired
  private CompanyService companyService;

  @Autowired
  private ComputerDTOMapper computerDtoMapper;

  @Autowired
  private ComputerValidator computerValidator;

  @RequestMapping(value = "/view/all", method = RequestMethod.GET)
  public ModelAndView getViewAll(@RequestParam(value = "search", required = false) String search,
      @RequestParam(value = ORDER_BY_KEY, required = false, defaultValue = "id") String order,
      @RequestParam(value = NB_ELEMENT_PAGE_KEY, required = false, defaultValue = "10") String paramPageSize,
      @RequestParam(value = PAGE_KEY, required = false, defaultValue = "1") String paramPageNumber,
      @RequestParam(value = SORT_KEY, required = false, defaultValue = "asc") String sort) {

    Page<ComputerDTO> page = new Page.Builder<ComputerDTO>().build();
    Map<String, Object> mapResponse = new HashMap<>();
    SearchComputer searchComputer = new SearchComputer();
    searchComputer.setPage(page);
    searchComputer.setNameToSearch(search);
    searchComputer.setSort(sort.toLowerCase());
    searchComputer.setOrder(order);

    long nbElementTotal = computerService.getNumberFindBySearch(searchComputer);
    int pageSize = Parser.parseToInteger(paramPageSize);
    pageSize = pageSize != 0 ? pageSize : 10;

    int nbPageTotal = (int) Math.ceil((double) nbElementTotal / pageSize);
    nbPageTotal = nbPageTotal != 0 ? nbPageTotal : 1;
    int pageNumber = Parser.parseToInteger(paramPageNumber);
    pageNumber = pageNumber != 0 ? pageNumber : 1;
    if (pageNumber > nbPageTotal) {
      pageNumber = nbPageTotal;
    }

    page.setNbElementTotal(nbElementTotal);
    page.setNbPageTotal(nbPageTotal);
    page.setNbCurrentPage(pageNumber);
    page.setPageSize(pageSize);

    List<Computer> computers = computerService.findBySearch(searchComputer);

    List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
    for (Computer computer : computers) {
      computersDTO.add(computerDtoMapper.map(computer));
    }
    page.setElements(computersDTO);
    mapResponse.put(ORDER_BY_KEY, searchComputer.getOrder().getName());
    mapResponse.put(SORT_KEY, searchComputer.getSort());
    mapResponse.put(PAGE_KEY, page);
    return new ModelAndView(DASHBOARD_JSP, mapResponse);
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public ModelAndView getAdd() {
    List<Company> companies = companyService.findAll();
    return new ModelAndView(ADD_JSP, "companies", companies);
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ModelAndView postAdd(@Valid ComputerDTO computerDTO, BindingResult bindingResults) {

    if (bindingResults.hasErrors()) {
      return new ModelAndView(ADD_JSP);
    }
    Computer computer = computerDtoMapper.unmap(computerDTO);
    computerValidator.isValid(computer);
    computerService.add(computer);
    return new ModelAndView("redirect:" + PATH_DASHBOARD);
  }

  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public ModelAndView getEdit(@RequestParam(value = "id", required = true) String paramId) {

    long id = Parser.parseToLong(paramId);
    Computer computer = computerService.find(id);
    ComputerDTO computerDTO = computerDtoMapper.map(computer);
    List<Company> companies = companyService.findAll();

    Map<String, Object> mapResponse = new HashMap<>();
    mapResponse.put("companies", companies);
    mapResponse.put("computer", computerDTO);

    return new ModelAndView(EDIT_JSP, mapResponse);
  }

  @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
  public ModelAndView postEdit(@RequestParam(value = ID_KEY, required = true) String id,
      @RequestParam(value = NAME_KEY, required = true) String name,
      @RequestParam(value = INTRODUCED_KEY, required = false) String introduced,
      @RequestParam(value = DISCONTINUED_KEY, required = false) String discontinued,
      @RequestParam(value = COMPANY_KEY, required = false) String companyId) {

    ComputerDTO computerDTO = new ComputerDTO(id, name, introduced, discontinued, companyId, "");
    Computer computer = computerDtoMapper.unmap(computerDTO);
    computerValidator.isValid(computer);
    computerService.update(computer);

    return new ModelAndView("redirect:" + PATH_DASHBOARD);
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public ModelAndView postDelete(
      @RequestParam(value = SELECTION_KEY, required = true) String selection) {

    String[] ids = selection.split(",");
    ArrayList<Computer> computers = new ArrayList<>();

    for (int i = 0; i < ids.length; i++) {
      long id = Parser.parseToLong(ids[i]);
      computers.add(computerService.find(id));
    }
    computerService.deleteMultiple(computers);
    return new ModelAndView("redirect:" + PATH_DASHBOARD);
  }
}
