package com.excilys.test.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.ComputerService;

import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class ComputerServiceTest extends TestCase {

  @Mock
  ComputerService computerService;

  @Mock
  Company company;

  LocalDateTime local1;
  LocalDateTime local2;
  LocalDateTime local3;
  LocalDateTime local4;

  @Override
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    local1 = LocalDateTime.of(2016, 04, 26, 0, 0);
    local2 = LocalDateTime.of(2016, 04, 27, 0, 0);
    local3 = LocalDateTime.of(2016, 04, 28, 0, 0);
    local4 = LocalDateTime.of(2016, 04, 29, 0, 0);

    Computer computer1 = new Computer.Builder().id(1L).name("computer1").introduced(local1)
        .discontinued(local2).build();
    Computer computer2 = new Computer.Builder().id(2L).name("computer2").introduced(local3)
        .discontinued(local4).build();

    ArrayList<Computer> computers = new ArrayList<Computer>();

    computers.add(computer1);
    computers.add(computer2);

    computerService = ComputerService.getInstance();
  }

  @Test
  public void testFind() {
    Computer computer = computerService.find(1);
    assertEquals(1, computer.getId());
  }

  @Test
  public void testFindAll() {
    ArrayList<Computer> computers = (ArrayList<Computer>) computerService.findAll(0, 15);
    assertEquals(15, computers.size());
  }

  @Test
  public void testAdd() {
    Computer computer = new Computer.Builder().name("computer1").introduced(local1)
        .discontinued(local2).build();
    long id = computerService.add(computer);
    computer.setId(id);
    assertEquals(-1, computer.getId());
  }

}
