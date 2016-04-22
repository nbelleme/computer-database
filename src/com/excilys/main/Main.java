package com.excilys.main;

import com.excilys.persistence.DaoException;
import com.excilys.service.ComputerService;

public class Main {

    public static void main(String[] args) {
        new DaoException(new Exception("Test"));
        ComputerService computerService = ComputerService.getInstance();
        computerService.findAll(-1,15);
        // new CommandLineInterface().run();
    }

}
