package com.paad.testtask.remote;

public interface ApiResult<T> {

    void onSuccess(T result);
    void onFail();

}
