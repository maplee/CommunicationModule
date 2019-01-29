package com.matt.connection.openapi;

/**
 * Author:Created by Matt on 2018/7/18.
 */

public interface IPipe {

    // 当前app
    String getPackage();
    // 无结果的传输数据
    void result(String source,String data);
    // 有结果的传输数据
    String query(String param);
}
