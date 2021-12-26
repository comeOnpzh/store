package com.example.store.service.ex;

/**
 * Created by pengzh5 Cotter on 2021/12/24.
 */
public class DeleteDeniedException extends ServiceExcetion{
    public DeleteDeniedException() {
        super();
    }

    public DeleteDeniedException(String message) {
        super(message);
    }

    public DeleteDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteDeniedException(Throwable cause) {
        super(cause);
    }

    protected DeleteDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
