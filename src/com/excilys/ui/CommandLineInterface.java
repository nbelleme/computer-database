package com.excilys.ui;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

public class CommandLineInterface {

    private ComputerService computerService;
    private CompanyService companyService;
    private boolean isRunning;
    Scanner scan;

    public CommandLineInterface() {
        computerService = ComputerService.getInstance();
        companyService = CompanyService.getInstance();
        isRunning = true;
        scan = new Scanner(System.in);
    }

    private void displayCompany(long id) {

    }

    private void displayComputer(long id) {

    }

    private void listAllCompanies(int firstRow) {

    }

    private void listAllComputers(int firstRow) {

    }

    private void mainMenu() {
        System.out.println("***Main Menu***");
        System.out.println("1/ List all computers");
        System.out.println("2/ List all companies");
        System.out.println("3/ Find a computer");
        System.out.println("4/ Find a company");
        System.out.println("5/ Create a computer");
        System.out.println("6/ Update a computer");
        System.out.println("7/ Delete a computer");
        System.out.println("in");

        while (!scan.hasNextInt()) {
            System.out.println("That's not a valid number !");
            scan.next();
        }

        System.out.println("out");
    }

    public void run() {
        while (isRunning) {
            mainMenu();
        }
        System.out.println("Program exited, goodbye !");
    }

    private void createComputer() {

    }
}
