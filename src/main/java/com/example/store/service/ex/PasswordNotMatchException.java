package com.example.store.service.ex;

/**
 * Created by pengzh5 Cotter on 2021/12/7.
 * 密码错误异常、用户名和密码不匹配异常
 */
public class PasswordNotMatchException extends ServiceExcetion{
    public PasswordNotMatchException() {
        super();
    }

    public PasswordNotMatchException(String message) {
        super(message);
    }

    public PasswordNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotMatchException(Throwable cause) {
        super(cause);
    }

    protected PasswordNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
