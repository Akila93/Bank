package com.app.bank.util;

import java.util.Arrays;
import java.util.Scanner;

public class IOUtil {
    final private static Scanner scanner = new Scanner(System.in);

    public static String readInput() {
        return scanner.nextLine();
    }

    public static void printBanner(String msg) {
        System.out.println(msg);
    }

    public static boolean validateOperationCodeInput(String userInput) {
        return Arrays.asList("D", "W", "P", "Q").stream().anyMatch(op -> op.equalsIgnoreCase(userInput));
    }


    public static void validateAmountInput(String amount) throws ModuleException {
        try {
            Double.parseDouble(amount);
        } catch (NumberFormatException numberFormatException){
            throw new ModuleException("Invalid amount entered",numberFormatException);
        }
    }
}
