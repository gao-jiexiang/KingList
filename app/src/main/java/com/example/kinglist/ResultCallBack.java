package com.example.kinglist;

public interface ResultCallBack<T> {
    void onSecuss(T t);
    void onFail(String msg);
}
