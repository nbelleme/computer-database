package com.excilys.model;

import java.time.LocalDate;

public class Computer {
  private long id;
  private String name;
  private LocalDate introduced;
  private LocalDate discontinued;
  private Company company;

  /**
   * Default constructor.
   */
  public Computer() {
    id = -1;
    introduced = LocalDate.now();
  }

  /**
   * @param builder
   *          builder
   */
  public Computer(Builder builder) {
    id = builder.computer.id;
    name = builder.computer.name;
    introduced = builder.computer.introduced;
    discontinued = builder.computer.discontinued;
    company = builder.computer.company;
  }

  public static class Builder {
    private Computer computer = new Computer();

    /**
     * @param id
     *          id to be built
     * @return builder builder
     */
    public Builder id(long id) {
      computer.id = id;
      return this;
    }

    /**
     * @param name
     *          name to be built
     * @return builder builder
     */
    public Builder name(String name) {
      computer.name = name;
      return this;
    }

    /**
     * @param introduced
     *          date to be built
     * @return builder builder
     */
    public Builder introduced(LocalDate introduced) {
      computer.introduced = introduced;
      return this;
    }

    /**
     * @param discontinued
     *          data to be build
     * @return builder builder
     */
    public Builder discontinued(LocalDate discontinued) {
      computer.discontinued = discontinued;
      return this;
    }

    /**
     * @param company
     *          company to be built
     * @return builder builder
     */
    public Builder company(Company company) {
      computer.company = new Company(company);
      return this;
    }

    /**
     * Call to constructor with builder.
     *
     * @return Computer computer built
     */
    public Computer build() {
      return new Computer(this);
    }
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getIntroduced() {
    return introduced;
  }

  public void setIntroduced(LocalDate introduced) {
    this.introduced = introduced;
  }

  public LocalDate getDiscontinued() {
    return discontinued;
  }

  public void setDiscontinued(LocalDate discontinued) {
    this.discontinued = discontinued;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

}
