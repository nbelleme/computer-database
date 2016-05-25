package com.excilys.util;

public class ParserException extends RuntimeException {
  private static final long serialVersionUID = -9190459468465695983L;

  public ParserException(Exception e) {
    super(e);
  }

  public ParserException(String s) {
    super(s);
  }
}
