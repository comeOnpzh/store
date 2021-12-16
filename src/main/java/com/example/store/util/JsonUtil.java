package com.example.store.util;

import java.io.Serializable;

/**
 * Created by pengzh5 Cotter on 2021/12/5.
 */
public class JsonUtil<E> implements Serializable {
    private Integer state;             //请求状态码
    private String message;             //抛出的错误描述
    private E data;

    public JsonUtil() {
    }

    public JsonUtil(Integer state) {
        this.state = state;
    }

    public JsonUtil(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonUtil(Integer state,E data) {
        this.state = state;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
