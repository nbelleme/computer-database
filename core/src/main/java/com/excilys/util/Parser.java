package com.excilys.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Parser {
    private static final String REGEX_INT = "^[-]?\\d*$";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static Integer parseToInteger(String param) {
        if (param != null) {
            Pattern patternElementPage = Pattern.compile(REGEX_INT);
            if (patternElementPage.matcher(param).matches()) {
                return Integer.parseInt(param);
            }
        }
        return null;
    }

    public static Long parseToLong(String param) {
        if (param != null && param != "") {
            Pattern patternElementPage = Pattern.compile(REGEX_INT);
            if (patternElementPage.matcher(param).matches()) {
                return Long.parseLong(param);
            }
        }
        return null;
    }

    public static LocalDate parseToLocalDate(String param) {
        if (param != "" && param != null) {
            if (param.contains("/")) {
                param = param.replace("/", "");
            } else {
                param = param.replace("-", "");
            }
            return LocalDate.parse(param, DATE_FORMATTER);
        }
        return null;
    }
}
