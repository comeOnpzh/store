package com.example.store.service.ex;

/**
 * Created by pengzh5 Cotter on 2022/1/11.
 */
public class PayMentException extends ServiceExcetion{
    public PayMentException() {
        super();
    }

    public PayMentException(String message) {
        super(message);
    }

    public PayMentException(String message, Throwable cause) {
        super(message, cause);
    }

    public PayMentException(Throwable cause) {
        super(cause);
    }

    protected PayMentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
