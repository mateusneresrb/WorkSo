package dev.mateusneres.scaling.utils;


import java.util.logging.Logger;

public class LoggerUtil {

    public static void info(String message) {
        Logger.getGlobal().info(message);
    }

    public static void error(String message) {
        Logger.getGlobal().severe(message);
    }

}
