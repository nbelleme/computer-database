package ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.excilys.model.Computer;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.ComputerDAO;

public class CommandLineInterface {

    private ComputerDAO computerDAO;
    private CompanyDAO companyDAO;
    private boolean isRunning;
    private int input;
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

    public void run() {
        while (isRunning) {
            mainMenu();
        }
        System.out.println("Program exited, goodbye !");
    }

    private void listAllComputers(int firstRow) {
        try {
            System.out.println("+-----+-----------------+---------------------+--------------+------------+");
            System.out.println("| id  | name            | introduced          | discontinued | company_id |");
            System.out.println("+-----+-----------------+---------------------+--------------+------------+");
            ArrayList<Computer> computers = computerDAO.findAll(firstRow, 15);
            for(Computer computer: computers){
                computer.toString();
            }
            System.out.println("0/ Main menu");
            System.out.println("1/ Next page");
            System.out.println("2/ Previous page");
            System.out.println("-1/ Leave Program");
            input = scan.nextInt();
            
            switch(input){
            case 0 :
                mainMenu();
                break;
            case 1:
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
        System.out.println("4/ List A Company");
        System.out.println("Type -1 to leave");
        int input = scan.nextInt();
        switch (input) {
        case 1:
            listAllComputers(0);
            break;
        case 2:
            break;
        case 3:
            break;
        case 4:
            break;
        }

        if (input == -1) {
            isRunning = !isRunning;
        }
    }

}
