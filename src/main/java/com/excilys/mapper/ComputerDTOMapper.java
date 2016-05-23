package com.excilys.mapper;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.regex.Pattern;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;

@Component
@Scope("singleton")
public class ComputerDTOMapper {

  public ComputerDTO map(Computer computer) {
    ComputerDTO computerDTO = new ComputerDTO();
    computerDTO.setId(String.valueOf(computer.getId()));
    computerDTO.setName(computer.getName());
    DecimalFormat df = new DecimalFormat("00");
    if (computer.getIntroduced() != null) {
      String introduced = computer.getIntroduced().getYear() + "-"
          + df.format(computer.getIntroduced().getMonthValue()) + "-"
          + df.format(computer.getIntroduced().getDayOfMonth());
      computerDTO.setIntroduced(introduced);
    } else {
      computerDTO.setIntroduced("");
    }
    if (computer.getDiscontinued() != null) {
      String discontinued = computer.getDiscontinued().getYear() + "-"
          + df.format(computer.getDiscontinued().getMonthValue()) + "-"
          + df.format(computer.getDiscontinued().getDayOfMonth());
      computerDTO.setDiscontinued(discontinued);
    } else {
      computerDTO.setDiscontinued("");
    }
    if (computer.getCompany() != null) {
      computerDTO.setIdCompany(String.valueOf(computer.getCompany().getId()));
      computerDTO.setNameCompany(computer.getCompany().getName());
    }
    return computerDTO;
  }

  public Computer unmap(ComputerDTO computerDTO) {
    Pattern patternId = Pattern.compile("^\\d+$");
    long id;
    if (patternId.matcher(computerDTO.getId()).matches()) {
      id = Integer.parseInt(computerDTO.getId());
    } else {
      id = -1;
    }

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
      long idCompany;
      if (patternId.matcher(computerDTO.getIdCompany()).matches()) {
        idCompany = Integer.parseInt(computerDTO.getIdCompany());
      } else {
        idCompany = -1;
      }

      company = new Company.Builder().id(idCompany).name(computerDTO.getNameCompany()).build();
    }

    return new Computer.Builder().id(id).name(name).introduced(introduced)
        .discontinued(discontinued).company(company).build();

  }
}
