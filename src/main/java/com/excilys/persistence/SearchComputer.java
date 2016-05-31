package com.excilys.persistence;

import java.util.List;

import org.springframework.stereotype.Component;

import com.excilys.model.Company;
import com.excilys.ui.MyPage;

@Component
public class SearchComputer {
  private String nameToSearch = "";
  private String sort;
  private ComputerColumns order;
  private MyPage page;
  private List<Company> companies;

  public String getNameToSearch() {
    return nameToSearch;
  }

  public void setNameToSearch(String name) {
    this.nameToSearch = name;
  }

  public ComputerColumns getOrder() {
    return order;
  }

  public void setOrder(String orderBy) {
    this.order = ComputerColumns.fromString(orderBy);
  }

  public String getSort() {
    return sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }

  public void setPage(MyPage page) {
    this.page = page;
  }

  public MyPage getPage() {
    return this.page;
  }

  public List<Company> getCompanyIds() {
    return companies;
  }

  public void setCompanyIds(List<Company> companies) {
    this.companies = companies;
  }

  public void setOrder(ComputerColumns order) {
    this.order = order;
  }

  public String createQuery() {
    String query = "";
    if (companies.size() != 0) {
      query += " OR company_id IN (";

      for (int i = 0; i < companies.size(); i++) {
        query += " " + companies.get(i).getId();
        if (i + 1 != companies.size()) {
          query += ",";
        }
      }
      query += ") ";

    }
    return query;
  }

}
