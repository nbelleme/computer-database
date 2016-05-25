package com.excilys.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Parser {
  private static final String REGEX_INT = "^[-]?\\d*$";
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

  public static int parseToInteger(String param) {
    Pattern patternElementPage = Pattern.compile(REGEX_INT);
    if (patternElementPage.matcher(param).matches()) {
      return Integer.parseInt(param);
    }
    throw new ParserException("Error parsing");
  }

  public static Long parseToLong(String param) {
    if (param != null && param != "") {
      Pattern patternElementPage = Pattern.compile(REGEX_INT);
      if (patternElementPage.matcher(param).matches()) {
        return Long.parseLong(param);
      }
    }
    throw new ParserException("Error parsing");
  }

  public static LocalDate parseToLocalDate(String param) {
    if (param != "") {
      if (param.contains("/")) {
        param = param.replace("/", "");
      } else {
        param = param.replace("-", "");
      }
      return LocalDate.parse(param, DATE_FORMATTER);
    }
    throw new ParserException("Error parsing");
  }
}
