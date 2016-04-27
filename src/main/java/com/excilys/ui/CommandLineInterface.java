package com.excilys.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.excilys.model.Computer;
import com.excilys.persistence.DaoException;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

public class CommandLineInterface {

  private ComputerService computerService;
  private CompanyService companyService;
  private boolean isRunning;
  Scanner scan;

  /**
   * Default constructor.
   */
  public CommandLineInterface() {
    computerService = ComputerService.getInstance();
    companyService = CompanyService.getInstance();
    isRunning = true;
    scan = new Scanner(System.in);
  }

  /**
   * Create computer.
   */
  public void createComputer() {

  }

  /**
   * Display one company.
   *
   * @param id
   *          id of the company to display
   */
  private void displayCompany(long id) {

  }

  /**
   * Display one computer.
   *
   * @param id
   *          id of the computer to display
   */
  private void displayComputer(long id) {

  }

  /**
   * List all companies.
   *
   * @param firstRow
   *          firstRow of research
   */
  private void listAllCompanies(int firstRow) {

  }

  /**
   * List all computers.
   *
   * @param firstRow
   *          firstRow of research
   */
  private void listAllComputers(int firstRow) {
    ArrayList<Computer> computers;
    try {
      computers = (ArrayList<Computer>) computerService.findSeveral(firstRow, 15);

      if (computers.size() == 0) {
        listAllComputers(firstRow - 15);
      }
      for (Computer computer : computers) {
        System.out.println(computer.toString());
      }

      System.out.println("\n\n");
      System.out.println("***Menu***");
      System.out.println("0/ Main Menu");
      System.out.println("1/ Next page");
      System.out.println("2/ Previous page");
      System.out.println("3/ Find a computer");
      int inputMenu = (int) scan();
      if (inputMenu < 5 && inputMenu > -1) {
        switch (inputMenu) {
        case 0:
          mainMenu();
          break;
        case 1:
          listAllComputers(firstRow + 15);
          break;
        case 2:
          if (firstRow == 0) {
            listAllComputers(firstRow);
          }
          break;
        case 3:
          long inputFindComputer = scan();
          displayComputer(inputFindComputer);
        }
      }
    } catch (DaoException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Main menu.
   */
  private void mainMenu() {
    System.out.println("***Main Menu***");
    System.out.println("1/ List all computers");
    System.out.println("2/ List all companies");
    System.out.println("3/ Find a computer");
    System.out.println("4/ Find a company");
    System.out.println("5/ Create a computer");
    System.out.println("6/ Update a computer");
    System.out.println("7/ Delete a computer");
    int inputMenu = (int) scan();
    if (inputMenu < 8 && inputMenu > 0) {
      switch (inputMenu) {
      case 1:
        listAllComputers(0);
        break;

      case 2:
        listAllCompanies(0);
        break;

      case 3:
        System.out.println("Enter computer's id : ");
        while (!scan.hasNextLong()) {
          System.out.println("That's not a valid number !");
          scan.next();
        }
        displayComputer(scan.nextLong());
        break;
      }
    }
  }

  /**
   * Run method.
   */
  public void run() {
    while (isRunning) {
      mainMenu();
    }
    System.out.println("Program exited, goodbye !");
  }

  /**
   * Scan user entry and return long.
   *
   * @return long long entered by the user
   */
  private long scan() {
    while (!scan.hasNextInt()) {
      System.out.println("That's not a valid number !");
      scan.next();
    }

    return scan.nextLong();
  }
}
