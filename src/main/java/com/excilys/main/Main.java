package com.excilys.main;

import com.excilys.model.Company;
import com.excilys.service.CompanyService;
import com.excilys.ui.CommandLineInterface;

public class Main {

  /**
   * @param args
   *          arguments of the program
   */
  public static void main(String[] args) {
    Company company = new Company.Builder().id(1).build();
    CompanyService companyService = CompanyService.getInstance();
    companyService.delete(company);
    // new CommandLineInterface().run();
  }

}
