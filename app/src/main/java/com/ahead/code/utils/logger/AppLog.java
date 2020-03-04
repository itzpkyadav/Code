package com.ahead.code.utils.logger;

import android.util.Log;

import com.ahead.code.BuildConfig;

public class AppLog implements CustomLog {

    @Override
    public void d(String TAG, String message) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, message);
        }
    }

    @Override
    public void i(String TAG, String message) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, message);
        }
    }

    @Override
    public void w(String TAG, String message) {
        if (BuildConfig.DEBUG) {
            Log.w(TAG, message);
        }
    }

    @Override
    public void e(String TAG, String message) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, message);
        }
    }
}
