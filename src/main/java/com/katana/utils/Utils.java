package com.katana.utils;

/**
 * Created by juan on 27/08/16.
 */
public class Utils {

    /**
     * @param options
     * @param optionName
     * @return
     */
    public static boolean contain(String[] options, String optionName) {
        for (String option : options) {
            if (option.equals(optionName)) {
                return true;
            }
        }

        return false;
    }

    private Utils() {

    }
}
