package ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.ComputerDAO;

public class CommandLineInterface {

    private ComputerDAO computerDAO;
    private CompanyDAO companyDAO;
    private boolean isRunning;
    Scanner scan;

    public CommandLineInterface() {
        try {
            computerDAO = new ComputerDAO();
            companyDAO = new CompanyDAO();
            isRunning = true;
            scan = new Scanner(System.in);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayCompany(Long id) {

    }

    private void displayComputer(Long id) {
        try {
            Computer computer = computerDAO.find(new Computer(id));
            System.out.println(computer.toString());

            System.out.println("0/ Main menu");
            if (computer.getCompany() != null) {
                System.out.println("1/ Display companies information");
            }
            System.out.println("-1/ Leave Program");

            while (!scan.hasNextInt()) {
                System.out.println("That's not a number!");
                scan.next(); // this is important!
            }
            int input = scan.nextInt();

            switch (input) {
            case 0:
                mainMenu();
                break;
            case 1:
                if (computer.getCompany() != null) {
                    // displayCompany(computer.getCompany().getId());
                } else {
                    displayCompany(computer.getCompany());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void listAllCompanies(int firstRow) {
        try {
            for (int clear = 0; clear < 1000; clear++) {
                System.out.println("\n");
            }

            ArrayList<Company> companies = companyDAO.findAll(firstRow, 15);
            for (Company company : companies) {
                System.out.println(company.toString());
            }

            System.out.println("0/ Main menu");
            if (companies.size() == 15) {
                System.out.println("1/ Next page");
            }
            if (firstRow - 15 >= 0) {
                System.out.println("2/ Previous page");
            }
            System.out.println("3/ First Page");
            System.out.println("-1/ Leave Program");
            while (!scan.hasNextInt()) {
                System.out.println("That's not a number!");
                scan.next(); // this is important!
            }
            int input = scan.nextInt();

            switch (input) {
            case 0:
                mainMenu();
                break;
            case 1:
                if (companies.size() == 15) {
                    listAllCompanies(firstRow + 15);
                } else {
                    listAllCompanies(firstRow);
                }
                break;
            case 2:
                if (!(firstRow - 15 >= 0)) {
                    listAllCompanies(firstRow - 15);
                } else {
                    listAllCompanies(firstRow);
                }
            case 3:
                listAllCompanies(0);
                break;
            case -1:
                isRunning = false;
                break;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void listAllComputers(int firstRow) {
        try {
            for (int clear = 0; clear < 1000; clear++) {
                System.out.println("\n");
            }
            System.out.println("+-----+-----------------+---------------------+--------------+------------+");
            System.out.println("| id  | name            | introduced          | discontinued | company_id |");
            System.out.println("+-----+-----------------+---------------------+--------------+------------+");
            ArrayList<Computer> computers = computerDAO.findAll(firstRow, 15);
            computers.toString();
            for (Computer computer : computers) {
                System.out.println(computer.toString());
            }

            System.out.println("-----------------------------------------------------------------------\n");

            System.out.println("0/ Main menu");
            if (computers.size() == 15) {
                System.out.println("1/ Next page");
            }
            if (firstRow - 15 >= 0) {
                System.out.println("2/ Previous page");
            }
            System.out.println("3/ First Page");
            System.out.println("-1/ Leave Program");
            int input = scan.nextInt();

            switch (input) {
            case 0:
                mainMenu();
                break;
            case 1:
                if (computers.size() == 15) {
                    listAllComputers(firstRow + 15);
                } else {
                    listAllComputers(firstRow);
                }
                break;
            case 2:
                if (!(firstRow - 15 >= 0)) {
                    listAllComputers(firstRow - 15);
                } else {
                    listAllComputers(firstRow);
                }
            case 3:
                listAllComputers(0);
                break;
            case -1:
                isRunning = false;
                break;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void mainMenu() {
        System.out.println("Hello");
        System.out.println("1/ List All Computers");
        System.out.println("2/ List All Companies");
        System.out.println("3/ Find A Computer");
        System.out.println("4/ Find A Company");
        System.out.println("5/ Create a computer");
        System.out.println("Type -1 to leave");
        while (!scan.hasNextInt()) {
            System.out.println("That's not a number!");
            scan.next(); // this is important!
        }
        int input = scan.nextInt();
        switch (input) {
        case 1:
            listAllComputers(0);
            break;
        case 2:
            listAllCompanies(0);
            break;
        case 3:
            System.out.println("Enter computer's id : ");
            while (!scan.hasNextInt()) {
                System.out.println("That's not a number!");
                scan.next(); // this is important!
            }

            long input2 = scan.nextLong();

            displayComputer(input2);
            break;
        case 4:
            break;
        case 5:
            createComputer();
            break;
        }

        if (input == -1) {
            isRunning = !isRunning;
        }
    }

    public void run() {
        while (isRunning) {
            mainMenu();
        }
        System.out.println("Program exited, goodbye !");
    }

    private void createComputer() {
        Computer computer = new Computer();
    }
}
