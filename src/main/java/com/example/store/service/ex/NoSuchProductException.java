package com.example.store.service.ex;

/**
 * Created by pengzh5 Cotter on 2022/1/2.
 */
public class NoSuchProductException extends ServiceExcetion{
    public NoSuchProductException() {
        super();
    }

    public NoSuchProductException(String message) {
        super(message);
    }

    public NoSuchProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchProductException(Throwable cause) {
        super(cause);
    }

    protected NoSuchProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
