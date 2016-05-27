package com.excilys.persistence;

public enum OrderBy {
  ID("computer.id", "id"), NAME("computer.name", "name"), INTRODUCED("computer.introduced",
      "introduced"), DISCONTINUED("computer.discontinued",
          "discontinued"), COMPANY("company_id", "company");

  String column;
  String name;

  OrderBy(String column, String name) {
    this.name = name;
    this.column = column;
  }

  public String getColumn() {
    return column;
  }

  public String getName() {
    return name;
  }

  public static OrderBy getOrderBy(String param) {
    if (param != null) {
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
    }
    return ID;
  }

}
