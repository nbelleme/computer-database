package com.excilys.test.model;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.DaoException;
import com.excilys.service.ComputerService;

import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class ComputerServiceTest extends TestCase {

  ComputerService computerService;

  @Mock
  Company company;

  LocalDate local1;
  LocalDate local2;

  Computer computer;

  @Override
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    local1 = LocalDate.of(2016, 04, 26);
    local2 = LocalDate.of(2016, 04, 27);

    computer = new Computer.Builder().name("name").introduced(local1).discontinued(local2).build();

    computerService = ComputerService.getInstance();
  }

  @Test
  public void findTest() {
    try {
      Computer computer = computerService.find(1);
      assertEquals(1, computer.getId());
    } catch (DaoException e) {
      e.printStackTrace();
    }
  }

  // @Test
  // public void findAllTest() {
  // try {
  // ArrayList<Computer> computers = (ArrayList<Computer>) computerService.findAll(0, 15);
  // assertEquals(15, computers.size());
  // } catch (DaoException e) {
  // e.printStackTrace();
  // }
  // }

  @Test
  public void addAllOkTest() {
    try {
      Computer computer = new Computer.Builder().name("computer1").introduced(local1)
          .discontinued(local2).build();
      Computer computer2 = computerService.add(computer);
      assertEquals(true, computer.equals(computer2));
    } catch (DaoException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void addNameNullTest() {
    Computer computer = new Computer.Builder().name(null).introduced(local1).discontinued(local2)
        .build();
    try {
      Computer computer2 = computerService.add(computer);
      assertEquals(true, computer.equals(computer2));
    } catch (DaoException e) {
      e.printStackTrace();
    }
  }

}
