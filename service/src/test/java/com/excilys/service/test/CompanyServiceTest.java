package com.excilys.service.test;

import com.excilys.model.Computer;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-applicationContext.xml")
public class CompanyServiceTest extends TestCase {

    @Autowired
    ComputerService computerService;

    @Autowired
    CompanyService companyService;

    @Before
    public void setDatabase() {
        for (int i = 0; i < 4; i++) {
            computerService.save(new Computer.Builder().id((long) i).build());
        }
    }

    @After
    public void cleanDatabase() {
        for (int i = 0; i < 4; i++) {
            computerService.delete(new Computer.Builder().id((long) i).build());
        }
    }

    @Test
    public void testAdd() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testFind() throws Exception {
    }

    @Test
    public void testFindAll() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

}