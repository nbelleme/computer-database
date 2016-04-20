package com.excilys.model;

import java.sql.Timestamp;

public class Computer {
    private Long id;
    private String name;
    private Timestamp introduced;
    private Timestamp discontinued;
    private Long company;

    public Computer() {
        name = null;
        introduced = null;
        discontinued = null;
        company = (Long) null;
    }

    public Computer(Long id) {
        this.id = id;
    }

    public Computer(Long id, Long company) {
        this.id = id;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getIntroduced() {
        return introduced;
    }

    public void setIntroduced(Timestamp introduced) {
        this.introduced = introduced;
    }

    public Timestamp getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Timestamp discontinued) {
        this.discontinued = discontinued;
    }

    public Long getCompany() {
        return company;
    }

    public void setCompany(Long company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return id + "   " + name + "     " + introduced + "     " + discontinued + "     " + company;
    }

}
