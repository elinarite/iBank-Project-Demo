package ru.k2.ibank.util.exceptions;

import jdk.jshell.spi.ExecutionControl;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message){super (message);}
}
