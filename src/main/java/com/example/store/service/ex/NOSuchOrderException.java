package com.example.store.service.ex;

/**
 * Created by pengzh5 Cotter on 2022/1/11.
 */
public class NOSuchOrderException extends ServiceExcetion{
    public NOSuchOrderException() {
        super();
    }

    public NOSuchOrderException(String message) {
        super(message);
    }

    public NOSuchOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public NOSuchOrderException(Throwable cause) {
        super(cause);
    }

    protected NOSuchOrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
