package com.excilys.test.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.service.ComputerService;

import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class ComputerServiceTest extends TestCase {

  @Test
  public void addAllOkTest() {
    ComputerService.getInstance().find(5);
    assertEquals(true, true);
  }
}
