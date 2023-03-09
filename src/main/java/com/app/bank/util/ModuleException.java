package com.app.bank.util;

public class ModuleException extends RuntimeException {

    public ModuleException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public ModuleException(String errorMessage) {
        super(errorMessage);
    }
}
