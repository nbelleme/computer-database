package com.excilys.controller;

import com.excilys.binding.mapper.computer.ComputerDTOMapper;
import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.ComputerColumns;
import com.excilys.service.ICompanyService;
import com.excilys.service.IComputerService;
import com.excilys.util.Parser;
import com.excilys.validator.ComputerValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/computer")
public class ComputerController {

    private static final String USER_KEY = "user";

    private static final String ORDER_BY_KEY = "orderBy";
    private static final String SORT_KEY = "orderSort";
    private static final String PAGE_KEY = "page";
    private static final String NB_ELEMENT_PAGE_KEY = "nbElementPage";
    private static final String SEARCH_KEY = "search";

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
    private IComputerService computerService;

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private ComputerDTOMapper computerDtoMapper;

    @Autowired
    private ComputerValidator computerValidator;

    private Logger logger = LoggerFactory.getLogger(ComputerController.class);

    @RequestMapping(value = "/view/all", method = RequestMethod.GET)
    public ModelAndView getViewAll(@RequestParam(value = SEARCH_KEY, required = false) String search,
                                   @RequestParam(value = ORDER_BY_KEY, required = false, defaultValue = "id") String paramOrder,
                                   @RequestParam(value = NB_ELEMENT_PAGE_KEY, required = false, defaultValue = "10") String paramPageSize,
                                   @RequestParam(value = PAGE_KEY, required = false, defaultValue = "0") String paramPageNumber,
                                   @RequestParam(value = SORT_KEY, required = false, defaultValue = "asc") String sort) {

        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = null;
        if (object.getClass() != String.class) {
            user = (User) object;
        }

        String order = ComputerColumns.fromString(paramOrder).getName();
        Direction direction = Sort.Direction.ASC;
        if (sort.toLowerCase().equals("desc") || sort.toLowerCase().equals("asc")) {
            direction = Sort.Direction.fromString(sort);
        }

        Sort sortRequest = new Sort(direction, order);
        int pageNumber = Parser.parseToInteger(paramPageNumber);
        int pageSize = Parser.parseToInteger(paramPageSize);
        int firstRow = pageNumber * pageSize;
        Pageable limit = new PageRequest(firstRow, pageSize, sortRequest);
        Company company = new Company.Builder().name(search).build();
        Page<Computer> computers;
        if (search != null && search != "") {
            computers = computerService.findByNameOrCompany(search, company, limit);
        } else {
            computers = computerService.findAll(limit);
        }
        List<ComputerDTO> computersDTO = new ArrayList<>();
        for (Computer computer : computers) {
            computersDTO.add(computerDtoMapper.map(computer));
        }

        Map<String, Object> mapResponse = new HashMap<>();

        mapResponse.put(USER_KEY, user);
        mapResponse.put(ORDER_BY_KEY, order);
        mapResponse.put(SORT_KEY, sort);
        mapResponse.put(SEARCH_KEY, search);
        mapResponse.put(PAGE_KEY, computers.getNumber());
        mapResponse.put("computers", computersDTO);
        mapResponse.put("total", computers.getTotalElements());
        return new ModelAndView(DASHBOARD_JSP, mapResponse);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView getAdd() {
        List<Company> companies = companyService.findAll();
        logger.info("IM IN");
        return new ModelAndView(ADD_JSP, "companies", companies);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView postAdd(@Valid ComputerDTO computerDTO, BindingResult bindingResults) {

        if (bindingResults.hasErrors()) {
            return new ModelAndView(ADD_JSP);
        }
        Computer computer = computerDtoMapper.unmap(computerDTO);
        computerValidator.assertValid(computer);
        computerService.save(computer);
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

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView postEdit(@RequestParam(value = ID_KEY, required = true) String id,
                                 @RequestParam(value = NAME_KEY, required = true) String name,
                                 @RequestParam(value = INTRODUCED_KEY, required = false) String introduced,
                                 @RequestParam(value = DISCONTINUED_KEY, required = false) String discontinued,
                                 @RequestParam(value = COMPANY_KEY, required = false) String companyId) {
        ComputerDTO computerDTO = new ComputerDTO(id, name, introduced, discontinued, companyId, "");
        Computer computer = computerDtoMapper.unmap(computerDTO);
        computerValidator.assertValid(computer);
        computerService.save(computer);

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
