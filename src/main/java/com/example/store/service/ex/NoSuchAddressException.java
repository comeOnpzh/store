package com.example.store.service.ex;

/**
 * Created by pengzh5 Cotter on 2021/12/26.
 */
public class NoSuchAddressException extends ServiceExcetion{
    public NoSuchAddressException() {
        super();
    }

    public NoSuchAddressException(String message) {
        super(message);
    }

    public NoSuchAddressException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchAddressException(Throwable cause) {
        super(cause);
    }

    protected NoSuchAddressException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
