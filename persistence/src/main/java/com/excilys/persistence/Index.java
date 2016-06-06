package com.excilys.persistence;

public enum Index {

  NAME("computer_name"), INTRODUCED("index_computer_introduced"), DISCONTINUED(
      "index_computer_discontinued");

  private String index;

  Index(String index) {
    this.index = index;
  }

  public String getIndex() {
    return index;
  }

}
