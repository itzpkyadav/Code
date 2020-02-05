package com.ahead.code.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class AppConstants {

    public static final String STRING_DATE_FORMAT = "dd-MM-yyyy";

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(STRING_DATE_FORMAT, Locale.ENGLISH);
}
