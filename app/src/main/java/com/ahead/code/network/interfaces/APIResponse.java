package com.ahead.code.network.interfaces;

public interface APIResponse {
    void OnResponseAPI(Object object);
    void OnErrorAPI(String error);
}
