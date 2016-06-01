package com.excilys.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class ComputerDTO {

  private static final String REGEX_DATE = "(^\\d{4}-\\d{2}-\\d{2}$)|(^$)";

  private String id;

  @NotNull
  @Size(min = 2)
  private String name;

  @Pattern(regexp = REGEX_DATE)
  private String introduced;
  @Pattern(regexp = REGEX_DATE)
  private String discontinued;
  private String idCompany;
  private String nameCompany;

  public ComputerDTO() {

  }

  public ComputerDTO(String id, String name, String introduced, String discontinued,
      String idCompany, String nameCompany) {
    super();
    this.id = id;
    this.name = name;
    this.introduced = introduced;
    this.discontinued = discontinued;
    this.idCompany = idCompany;
    this.nameCompany = nameCompany;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIntroduced() {
    return introduced;
  }

  public void setIntroduced(String introduced) {
    this.introduced = introduced;
  }

  public String getDiscontinued() {
    return discontinued;
  }

  public void setDiscontinued(String discontinued) {
    this.discontinued = discontinued;
  }

  public String getIdCompany() {
    return idCompany;
  }

  public void setIdCompany(String idCompany) {
    this.idCompany = idCompany;
  }

  public String getNameCompany() {
    return nameCompany;
  }

  public void setNameCompany(String companyName) {
    this.nameCompany = companyName;
  }
  
  @Override
  public String toString() {
    return "ComputerDTO [id=" + id + ", name=" + name + ", introduced=" + introduced
        + ", discontinued=" + discontinued + ", idCompany=" + idCompany + ", nameCompany="
        + nameCompany + "]";
  }  
}
