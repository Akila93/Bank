package com.app.bank.constant;

public enum OperationType {
    DEPOSIT("D"),
    WITHDRAW("W"),
    PRINT_STATEMENT("P"),
    QUIT("Q");

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    OperationType(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
