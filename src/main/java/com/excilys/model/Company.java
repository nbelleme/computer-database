package com.excilys.model;

public class Company {

  private long id;
  private String name;

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
    public Builder id(long id) {
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
    id = -1;
    name = null;
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
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    return true;
  }

  public long getId() {
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

  public void setId(long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Company [id=" + id + ", name=" + name + "]";
  }

}
