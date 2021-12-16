package com.example.store.service.ex;

/**
 * Created by pengzh5 Cotter on 2021/12/5.
 */
public class InsertDataException extends ServiceExcetion{
    public InsertDataException() {
        super();
    }

    public InsertDataException(String message) {
        super(message);
    }

    public InsertDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertDataException(Throwable cause) {
        super(cause);
    }

    protected InsertDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
