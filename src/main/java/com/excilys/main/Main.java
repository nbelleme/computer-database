package com.excilys.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

  /**
   * @param args
   *          arguments of the program
   */
  public static void main(String[] args) {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
        "applicationContext.xml");

    // if (!((BeanFactory) applicationContext).containsBean("ComputerService")) {
    // System.out.println("!ok");
    // } else {
    // System.out.println("ok");
    // }

    ((ConfigurableApplicationContext) applicationContext).close();
  }
  
}
