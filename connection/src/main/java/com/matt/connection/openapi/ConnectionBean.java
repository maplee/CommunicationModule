package com.matt.connection.openapi;

import java.io.Serializable;

/**
 * Author:Created by Matt on 2019/1/10.
 */

public class ConnectionBean implements Serializable {

    // 类型
    private int type;
    // 数据
    private String data;

    public ConnectionBean() {
    }

    public ConnectionBean(int type, String data) {
        this.type = type;
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
