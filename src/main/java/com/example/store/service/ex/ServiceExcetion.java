package com.example.store.service.ex;

/**
 * Created by pengzh5 Cotter on 2021/12/5.
 */

/**
 * 业务层异常的基类，所有业务层异常都要实现这个方法
 */
public class ServiceExcetion extends RuntimeException{
    public ServiceExcetion() {
        super();
    }

    public ServiceExcetion(String message) {
        super(message);
    }

    public ServiceExcetion(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceExcetion(Throwable cause) {
        super(cause);
    }

    protected ServiceExcetion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
