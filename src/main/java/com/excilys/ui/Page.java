package com.excilys.ui;

import java.util.ArrayList;

public class Page<T> {

  private int nbElementPage;
  private int nbElementTotal;
  private ArrayList<T> elements;

  private Page(Builder<T> builder) {
    nbElementPage = builder.page.nbElementPage;
    nbElementTotal = builder.page.nbElementTotal;
    elements = builder.page.elements;
  }

  public static class Builder<T> {
    private Page<T> page;

    public Builder<T> nbElementPage(int nbElementPage) {
      page.nbElementPage = nbElementPage;
      return this;
    }

    public Builder<T> nbElementTotal(int nbElementTotal) {
      page.nbElementTotal = nbElementTotal;
      return this;
    }

    public Builder<T> elements(ArrayList elements) {
      page.elements = elements;
      return this;
    }

    public Page<T> build() {
      return new Page(this);
    }
  }

}
