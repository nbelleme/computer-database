package com.excilys.persistence;

import javax.servlet.http.HttpServletRequest;

import com.excilys.ui.Page;

public class SearchComputer {
  private String name;
  private OrderBy orderBy;
  private String orderSort;

  private Page page;

  public SearchComputer(HttpServletRequest request) {
    name = request.getParameter("search");
    orderSort = request.getParameter("orderSort");

    orderBy(request.getParameter("orderBy"));
  }

  private void orderBy(String param) {
    if (param != null) {
      orderBy = OrderBy.getOrderBy(param);
    } else {
      orderBy = null;
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public OrderBy getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(OrderBy orderBy) {
    this.orderBy = orderBy;
  }

  public String getOrderSort() {
    return orderSort;
  }

  public void setOrderSort(String orderSort) {
    this.orderSort = orderSort;
  }

  public void setPage(Page page) {
    this.page = page;
  }

  public Page getPage() {
    return this.page;
  }

}
