package com.ahead.code.utils;

public class AppHelper {

    public static String twoDigitString(int number) {
        if (number > -1 && number < 10) {
            return "0" + number;
        }
        return String.valueOf(number);
    }
}
