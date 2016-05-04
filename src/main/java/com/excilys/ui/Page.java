package com.excilys.ui;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {

  private int nbElementPage;
  private int nbElementTotal;
  private int nbCurrentPage;
  private int nbPageTotal;
  private List<T> elements;

  private Page(Builder<T> builder) {
    nbElementPage = builder.page.nbElementPage;
    nbElementTotal = builder.page.nbElementTotal;
    elements = builder.page.elements;
  }

  private Page() {
    nbElementPage = 10;
    nbElementTotal = -1;
    nbCurrentPage = 1;
  }

  public static class Builder<T> {
    private Page<T> page = new Page();

    public Builder<T> nbPage(int nbPage) {
      page.nbCurrentPage = nbPage;
      return this;
    }

    public Builder<T> nbElementPage(int nbElementPage) {
      page.nbElementPage = nbElementPage;
      return this;
    }

    public Builder<T> nbElementTotal(int nbElementTotal) {
      page.nbElementTotal = nbElementTotal;
      return this;
    }

    public Builder<T> elements(List elements) {
      page.elements = elements;
      return this;
    }

    public Page<T> build() {
      return new Page(this);
    }
  }

  public int getNbElementPage() {
    return nbElementPage;
  }

  public void setNbElementPage(int nbElementPage) {
    this.nbElementPage = nbElementPage;
  }

  public List<T> getElements() {
    return elements;
  }

  public void setElements(List<T> elements) {
    this.elements = elements;
  }

  public int getNbElementTotal() {
    return nbElementTotal;
  }

  public void setNbElementTotal(int nbElementTotal) {
    this.nbElementTotal = nbElementTotal;
  }

  public int getNbCurrentPage() {
    return nbCurrentPage;
  }

  public void setNbCurrentPage(int nbPage) {
    this.nbCurrentPage = nbPage;
  }

  public int getNbPageTotal() {
    return nbPageTotal;
  }

  public void setNbPageTotal(int nbPageTotal) {
    this.nbPageTotal = nbPageTotal;
  }
}
