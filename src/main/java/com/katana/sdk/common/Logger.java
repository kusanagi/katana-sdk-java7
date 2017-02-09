package com.katana.sdk.common;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.logging.Level;

import static java.util.logging.Logger.getGlobal;

/**
 * Created by juan on 30/09/16.
 */
public class Logger {

    private static boolean isActive;

    private Logger() {

    }

    /**
     *
     */
    public static void activate() {
        isActive = true;
    }

    /**
     * @param message
     */
    public static void log(String message) {
        if (isActive) {
            logToStdout(getLog(message));
            logToFile(getLog(message));
        }
    }

    /**
     * @param e
     */
    public static void log(Exception e) {
        log(e.getMessage());
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            log(stackTraceElement.toString());
        }
    }

    private static String getLog(String message) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return dateFormat.format(Calendar.getInstance().getTime()) + " [DEBUG] [SDK] " + message;
    }

    private static void logToStdout(String message) {
        getGlobal().log(Level.INFO, message);
        System.out.println(message);
    }

    private static void logToFile(String message) {
        String[] arrayLines = message.split("\n");
        Path file = Paths.get("katana-log.txt");
        try {
            Files.write(file, Arrays.asList(arrayLines), Charset.forName("UTF-8"), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
