package com.example.store.service.ex;

/**
 * Created by pengzh5 Cotter on 2021/12/20.
 * 收货地址异常类，当收货地址数量大于20个时，触发此异常
 */
public class AddressCountException extends ServiceExcetion {
    public AddressCountException() {
        super();
    }

    public AddressCountException(String message) {
        super(message);
    }

    public AddressCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressCountException(Throwable cause) {
        super(cause);
    }

    protected AddressCountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
