package com.app.bank.util;

import com.app.bank.constant.OperationType;

import java.util.Arrays;
import java.util.Optional;

public class SimulatorUtil {
    public static Optional<OperationType> getOperationType(String nextLine) {
        return Arrays.stream(OperationType.values()).filter(op -> {
            return op.toString().equalsIgnoreCase(nextLine);
        }).findAny();
    }
}
