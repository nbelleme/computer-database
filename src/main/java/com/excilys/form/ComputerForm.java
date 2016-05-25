package com.excilys.form;

import javax.validation.constraints.Size;

public class ComputerForm {

  @Size(min = 2)
  private String name;
}
