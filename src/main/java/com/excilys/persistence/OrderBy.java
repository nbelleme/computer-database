package com.excilys.persistence;

public enum OrderBy {
  ID("id"), NAME("name"), INTRODUCED("introduced"), DISCONTINUED("discontinued"), COMPANY(
      "company_id");

  private String name;

  private OrderBy(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

}
