package com.excilys.controller;

import com.excilys.model.Computer;
import com.excilys.service.impl.ComputerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/webapp")
public class APIController {

    @Autowired
    private ComputerService computerService;

    @RequestMapping(value = "/getAll")
    public String getAll() {
        List<Computer> computers = computerService.findAll();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(computers);
            return jsonInString;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
