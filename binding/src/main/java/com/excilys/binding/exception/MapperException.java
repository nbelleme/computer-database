package com.excilys.binding.exception;

public class MapperException extends RuntimeException {

    public MapperException(String s, Exception e){
        super(s,e);
    }
}
