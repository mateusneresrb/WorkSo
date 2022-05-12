package dev.mateusneres.scaling.utils;


public class Logger {

    public static void info(String message) {
        System.out.println("[INFO] " + message + ConsoleColors.RESET);
    }

    public static void error(String message) {
        System.out.println(ConsoleColors.RED + "[ERROR] " + message + ConsoleColors.RESET);
    }

}
