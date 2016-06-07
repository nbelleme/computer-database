package com.excilys.cli;

public interface ICommandLineInterface {

    /**
     * Create computer.
     */
    void createComputer();

    /**
     * Display one company.
     *
     * @param id id of the company to display
     */
    void displayCompany(long id);

    /**
     * Display one computer.
     *
     * @param id id of the computer to display
     */
    void displayComputer(long id);

    /**
     * List all companies.
     *
     * @param firstRow firstRow of research
     */
    void listAllCompanies(int firstRow);

    /**
     * List all computers.
     *
     * @param firstRow firstRow of research
     */
    void listAllComputers(int firstRow);

    /**
     * Main menu.
     */
    void mainMenu();

    /**
     * Run method.
     */
    void run();

    /**
     * Scan user entry and return long.
     *
     * @return long long entered by the user
     */
    long scan();

    /**
     * Call URI
     */
    String callAPI(String uri);
}
