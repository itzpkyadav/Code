package com.ahead.code.data.network.interfaces;

public interface APIResponse {
    void OnResponseAPI(Object object);
    void OnErrorAPI(String error);
}
