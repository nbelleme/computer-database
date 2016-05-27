package com.excilys.ui;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Page<T> {

  private int pageSize;
  private long nbElementTotal;
  private int nbCurrentPage;
  private int nbPageTotal;
  private List<T> elements;

  private Page(Builder<T> builder) {
    pageSize = builder.page.pageSize;
    nbElementTotal = builder.page.nbElementTotal;
    elements = builder.page.elements;
  }

  private Page() {
    pageSize = 10;
    nbElementTotal = -1;
    nbCurrentPage = 1;
  }

  public static class Builder<T> {
    private Page<T> page = new Page<T>();

    public Builder<T> nbPage(int nbPage) {
      page.nbCurrentPage = nbPage;
      return this;
    }

    public Builder<T> nbElementPage(int nbElementPage) {
      page.pageSize = nbElementPage;
      return this;
    }

    public Builder<T> nbElementTotal(int nbElementTotal) {
      page.nbElementTotal = nbElementTotal;
      return this;
    }

    public Builder<T> elements(List<T> elements) {
      page.elements = elements;
      return this;
    }

    public Page<T> build() {
      return new Page<T>(this);
    }
  }

  public int getFirsRow() {
    return pageSize * (nbCurrentPage - 1);
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int nbElementPage) {
    this.pageSize = nbElementPage;
  }

  public List<T> getElements() {
    return elements;
  }

  public void setElements(List<T> elements) {
    this.elements = elements;
  }

  public long getNbElementTotal() {
    return nbElementTotal;
  }

  public void setNbElementTotal(long nbElementTotal) {
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
