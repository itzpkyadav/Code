package com.ahead.code.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class AppConstants {

    public static final String BASE_URL = "http://fathomless-shelf-5846.herokuapp.com/api/";

    public static final String STRING_DATETIME_FORMAT = "dd-MM-yyyy HH:mm";
    public static final String STRING_DATE_FORMAT = "dd-MM-yyyy";
    public static final String STRING_TIME_FORMAT = "HH:mm";

    public static final SimpleDateFormat SIMPLE_DATETIME_FORMAT = new SimpleDateFormat(STRING_DATETIME_FORMAT, Locale.ENGLISH);
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(STRING_DATE_FORMAT, Locale.ENGLISH);
    public static final SimpleDateFormat SIMPLE_TIME_FORMAT = new SimpleDateFormat(STRING_TIME_FORMAT, Locale.ENGLISH);
}
