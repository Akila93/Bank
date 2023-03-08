package com.app.bank.util;

import java.util.Scanner;

public class IOUtil {
    final private static Scanner scanner = new Scanner(System.in);

    public static String readInput(){
        return scanner.nextLine();
    }

    public static void printBanner(String msg){
        System.out.println(msg);
    }
}
