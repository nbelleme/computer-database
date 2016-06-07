package com.excilys.binding.mapper.computer;

import java.text.DecimalFormat;
import java.time.LocalDate;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.util.Parser;

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
    Long id = Parser.parseToLong(computerDTO.getId());
    String name = computerDTO.getName();
    LocalDate introduced = Parser.parseToLocalDate(computerDTO.getIntroduced());
    LocalDate discontinued = Parser.parseToLocalDate(computerDTO.getDiscontinued());
    Long idCompany = Parser.parseToLong(computerDTO.getIdCompany());
    Company company = null;
    if(computerDTO.getIdCompany() != "" && computerDTO.getIdCompany() != null){
      company = new Company.Builder().id(idCompany).build();
    }

    return new Computer.Builder().id(id).name(name).introduced(introduced)
        .discontinued(discontinued).company(company).build();

  }
}
