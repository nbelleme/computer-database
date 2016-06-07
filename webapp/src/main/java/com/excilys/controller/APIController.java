package com.excilys.controller;

import com.excilys.binding.mapper.JacksonMapper;
import com.excilys.binding.mapper.page.PageMapper;
import com.excilys.model.Computer;
import com.excilys.service.impl.ComputerService;
import com.excilys.util.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class APIController {

    @Autowired
    private ComputerService computerService;


    @RequestMapping(value = "/all")
    public String getAll() {
        Pageable limit = new PageRequest(0, 15);
        Page<Computer> computers = computerService.findAll(limit);
        MyPage myPage = PageMapper.springPageToMyPage(computers);

        return JacksonMapper.computersToJson(myPage);
    }

    @RequestMapping(value = "/find/{id}")
    public String getOne(@PathVariable Long id) {
        Computer computer = computerService.find(id);
        return JacksonMapper.objectToJson(computer);
    }
}
