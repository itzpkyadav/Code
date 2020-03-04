package com.ahead.code.utils.logger;

public interface CustomLog {
    void d(String TAG, String message);
    void i(String TAG, String message);
    void w(String TAG, String message);
    void e(String TAG, String message);
}
