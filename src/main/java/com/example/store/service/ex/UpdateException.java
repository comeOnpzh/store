package com.example.store.service.ex;

/**
 * Created by pengzh5 Cotter on 2021/12/8.
 * 数据更新时发生异常
 */
public class UpdateException extends ServiceExcetion{
    public UpdateException() {
        super();
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }

    protected UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
