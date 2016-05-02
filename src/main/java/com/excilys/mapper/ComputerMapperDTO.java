package com.excilys.mapper;

import java.time.LocalDate;

import com.excilys.model.Company;
import com.excilys.model.Computer;

import dto.ComputerDTO;

public enum ComputerMapperDTO {
  INSTANCE;

  public ComputerDTO map(Computer computer) {
    ComputerDTO computerDTO = new ComputerDTO();
    computerDTO.setId(String.valueOf(computer.getId()));
    computerDTO.setName(computer.getName());
    String introduced = computer.getIntroduced().getYear() + "-"
        + computer.getIntroduced().getMonthValue() + "-" + computer.getIntroduced().getDayOfMonth();
    computerDTO.setIntroduced(introduced);
    String discontinued = computer.getDiscontinued().getYear() + "-"
        + computer.getDiscontinued().getMonthValue() + "-"
        + computer.getDiscontinued().getDayOfMonth();
    computerDTO.setDiscontinued(discontinued);
    if (computer.getCompany() != null) {
      computerDTO.setIdCompany(String.valueOf(computer.getCompany().getId()));
      computerDTO.setNameCompany(computer.getCompany().getName());
    }
    return computerDTO;
  }

  public Computer unmap(ComputerDTO computerDTO) {
    long id = Long.parseLong(computerDTO.getId());
    String name = computerDTO.getName();

    LocalDate introduced = null;
    if (computerDTO.getIntroduced() != "" && computerDTO.getIntroduced() != null) {
      String[] introducedArray = computerDTO.getIntroduced().split("-");
      int year = Integer.parseInt(introducedArray[0]);
      int month = Integer.parseInt(introducedArray[1]);
      int day = Integer.parseInt(introducedArray[2]);
      introduced = LocalDate.of(year, month, day);
    }

    LocalDate discontinued = null;
    if (computerDTO.getDiscontinued() != "" && computerDTO.getDiscontinued() != null) {
      String[] discontinuedArray = computerDTO.getDiscontinued().split("-");
      int year = Integer.parseInt(discontinuedArray[0]);
      int month = Integer.parseInt(discontinuedArray[1]);
      int day = Integer.parseInt(discontinuedArray[2]);
      discontinued = LocalDate.of(year, month, day);
    }

    Company company = null;
    if (computerDTO.getIdCompany() != "" && computerDTO.getIdCompany() != null) {
      long idCompany = Long.parseLong(computerDTO.getId());
      company = new Company.Builder().id(idCompany).name(computerDTO.getNameCompany()).build();
    }

    return new Computer.Builder().id(id).name(name).introduced(introduced)
        .discontinued(discontinued).company(company).build();

  }
}
