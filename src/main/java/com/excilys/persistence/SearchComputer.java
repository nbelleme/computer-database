package com.excilys.persistence;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.excilys.ui.Page;

@Component
public class SearchComputer {
  private String nameToSearch;
  private String sort;
  private OrderBy order;
  private Page page;

  private void orderBy(String param) {

  }

  public String getNameToSearch() {
    return nameToSearch;
  }

  public void setNameToSearch(String name) {
    this.nameToSearch = name;
  }

  public OrderBy getOrder() {
    return order;
  }

  public void setOrder(String orderBy) {
    this.order = OrderBy.getOrderBy(orderBy);
  }

  public String getSort() {
    return sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }

  public void setPage(Page page) {
    this.page = page;
  }

  public Page getPage() {
    return this.page;
  }

}
