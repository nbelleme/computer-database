package com.excilys.persistence;

public enum OrderBy {
  ID("computer.id", "id", "id"), NAME("computer.name", "name", "name"), INTRODUCED(
      "computer.introduced", "introduced", "introduced"), DISCONTINUED("computer.discontinued",
          "discontinued", "discontinued"), COMPANY("company_id", "company", "company_id");

  String column;
  String name;
  String index;

  OrderBy(String column, String name, String index) {
    this.name = name;
    this.column = column;
    this.index = index;
  }

  public String getColumn() {
    return column;
  }

  public String getName() {
    return name;
  }

  public String getIndex() {
    return index;
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
