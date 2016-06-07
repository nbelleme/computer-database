package com.excilys.cli.com.excilys.cli.impl;

import com.excilys.binding.exception.MapperException;
import com.excilys.binding.mapper.JacksonMapper;
import com.excilys.cli.ICommandLineInterface;
import com.excilys.model.Computer;
import com.excilys.util.MyPage;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.springframework.data.domain.Page;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Scanner;

public class CommandLineInterface implements ICommandLineInterface {

    private static final String PROTOCOLE = "http://";
    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private static final String APP = "ComputerDatabase";
    private static final String BASE_URL = PROTOCOLE + HOST + ":" + PORT + "/" + APP +
            "/rest";

    private boolean isRunning;
    Scanner scanner;

    /**
     * Default constructor.
     */
    public CommandLineInterface() {
        isRunning = true;
        scanner = new Scanner(System.in);
    }

    @Override
    public void createComputer() {
        //TODO
    }

    @Override
    public void displayCompany(long id) {
        //TODO
    }

    @Override
    public void displayComputer(long id) {
        try {
            String json = callAPI("/find/" + id);
            Computer computer = (Computer) JacksonMapper.jsonToObject(json, Computer.class);
            System.out.println(computer.toString());
            mainMenu();
        } catch (MapperException e) {
            System.out.println("Error when getting computer with id : " + id);
        }
    }

    @Override
    public void listAllCompanies(int firstRow) {
        //TODO
    }

    @Override
    public void listAllComputers(int firstRow) {
        try {
            String json = callAPI("/all");
            MyPage<Computer> computers = JacksonMapper.jsonToComputers(json);

            for (Computer computer : computers.getElements()) {
                System.out.println(computer.toString());
            }
        } catch (MapperException e) {
            System.out.println("Error when getting computers");
            e.printStackTrace();
        }
        System.out.println("\n");
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
    }

    @Override
    public void mainMenu() {
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
                    while (!scanner.hasNextLong()) {
                        System.out.println("That'scanner not a valid number !");
                        scanner.next();
                    }
                    displayComputer(scanner.nextLong());
                    break;
            }
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            mainMenu();
        }
        System.out.println("Program exited, goodbye !");
    }

    @Override
    public long scan() {
        while (!scanner.hasNextInt()) {
            System.out.println("That'scanner not a valid number !");
            scanner.next();
        }

        return scanner.nextLong();
    }

    @Override
    public String callAPI(String uri) {
        Client client = Client.create();
        WebResource webResource = client.resource(BASE_URL + uri);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
        return response.getEntity(String.class);
    }
}
