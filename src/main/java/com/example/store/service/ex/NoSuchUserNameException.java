package com.example.store.service.ex;

/**
 * Created by pengzh5 Cotter on 2021/12/7.
 * 查无此人异常
 */
public class NoSuchUserNameException extends ServiceExcetion{
    public NoSuchUserNameException() {
        super();
    }

    public NoSuchUserNameException(String message) {
        super(message);
    }

    public NoSuchUserNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchUserNameException(Throwable cause) {
        super(cause);
    }

    protected NoSuchUserNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
