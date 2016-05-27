package com.excilys.model;

import org.springframework.stereotype.Component;

@Component
public class Company implements Comparable<Company> {

  private Long id;
  private String name;
  
  public Company(Long id){
    this.id = id;
  }

  public static class Builder {
    private Company company = new Company();

    /**
     * Calls Company constructor with builder.
     *
     * @return company company built
     */
    public Company build() {
      return new Company(this);
    }

    /**
     * @param id
     *          id to be built
     * @return builder builder
     */
    public Builder id(Long id) {
      company.id = id;
      return this;
    }

    /**
     * @param name
     *          name to be build
     * @return builder builder
     */
    public Builder name(String name) {
      company.name = name;
      return this;
    }

  }

  /**
   * Default constructor.
   */
  public Company() {
  }

  /**
   * Builder constructor.
   *
   * @param builder
   *          builder
   */
  public Company(Builder builder) {
    id = builder.company.id;
    name = builder.company.name;
  }

  /**
   * Construct by copy.
   *
   * @param company
   *          company that needs to be copied
   */
  public Company(Company company) {
    id = company.id;
    name = company.name;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Company other = (Company) obj;
    if (id != other.id) {
      return false;
    }

    return true;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + (int) (id ^ (id >>> 32));
    result = (prime * result) + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Company [id=" + id + ", name=" + name + "]";
  }

  @Override
  public int compareTo(Company o) {
    return o.name.compareTo(name);
  }

}
