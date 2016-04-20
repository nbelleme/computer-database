package com.excilys.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.ComputerDAO;
import com.excilys.persistence.Database;

import sun.tools.jar.CommandLine;
import ui.CommandLineInterface;

public class Main {

    public static void main(String[] args) {
        new CommandLineInterface().run();
    }

}
