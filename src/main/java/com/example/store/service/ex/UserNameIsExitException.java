package com.example.store.service.ex;

/**
 * Created by pengzh5 Cotter on 2021/12/5.
 */
public class UserNameIsExitException extends ServiceExcetion {
    public UserNameIsExitException() {
        super();
    }

    public UserNameIsExitException(String message) {
        super(message);
    }

    public UserNameIsExitException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNameIsExitException(Throwable cause) {
        super(cause);
    }

    protected UserNameIsExitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
