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

/**
 * Created by juan on 30/09/16.
 */
public class Logger {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");;

    private static boolean isActive;

    public static void activate(){
        isActive = true;
    }

    public static void log(String message) {
        if (isActive) {
            logToStdout(getLog(message));
//        logToFile(getLog(message));
        }
    }

    private static String getLog(String message) {
        return dateFormat.format(Calendar.getInstance().getTime()) + " [DEBUG] [SDK] " + message;
    }

    private static void logToStdout(String message){
        System.out.print(message + " \n");
    }

    private static void logToFile(String message) {
        String[] arrayLines = message.split("\n");
        Path file = Paths.get("/home/juan/Documents/katana-log.txt");
        try {
            Files.write(file, Arrays.asList(arrayLines), Charset.forName("UTF-8"), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void log(Exception e) {
        log(e.getMessage());
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement  stackTraceElement : stackTrace){
            log(stackTraceElement.toString());
        }
    }
}
