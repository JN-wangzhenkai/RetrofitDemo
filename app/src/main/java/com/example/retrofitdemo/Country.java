package com.example.retrofitdemo;

/**
 * Created by wangzhenkai on 2016/7/5.
 */
public class Country {
    String login;
    String contributions;

    @Override
    public String toString() {
        return login+","+contributions;
    }
}
