package com.excilys.persistence;

public enum OrderBy {
  ID("computer.id"), NAME("computer.name"), INTRODUCED("computer.introduced"), DISCONTINUED(
      "computer.discontinued"), COMPANY("company.name");

  String name;

  OrderBy(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public static OrderBy getOrderBy(String param) {
    switch (param) {
    case "id":
      return ID;
    case "name":
      return NAME;
    case "introduced":
      return INTRODUCED;
    case "discontinued":
      return DISCONTINUED;
    case "company":
      return COMPANY;
    }
    return OrderBy.ID;
  }

}
