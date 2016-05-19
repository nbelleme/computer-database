package com.excilys.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.model.Computer;
import com.excilys.service.ComputerService;

public class Main {

  /**
   * @param args
   *          arguments of the program
   */
  public static void main(String[] args) {
    // Company company = new Company.Builder().id(3).build();
    // CompanyService companyService = CompanyService.getInstance();
    // companyService.delete(company);
    // new CommandLineInterface().run();

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
        "applicationContext.xml");

    ComputerService computerService = (ComputerService) applicationContext
        .getBean("computerService");
    ComputerService computerService2 = (ComputerService) applicationContext
        .getBean("computerService");

    Computer computer = (Computer) applicationContext.getBean("computer");
    Computer computer2 = (Computer) applicationContext.getBean("computer");
    System.out.println("TEST computerService : ");
    System.out.println(computerService2 == computerService);

    computer.setName("bonjour");
    System.out.println("TEST computer : ");
    System.out.println(computer.toString());
    System.out.println(computer2.toString());
    System.out.println(computer == computer2);
    ((ConfigurableApplicationContext) applicationContext).close();
  }

}
