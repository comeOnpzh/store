package com.example.store.controller.ex;

/**
 * Created by pengzh5 Cotter on 2021/12/13.
 */
public class FileUpLoadIOException extends FileUpLoadException{
    public FileUpLoadIOException() {
        super();
    }

    public FileUpLoadIOException(String message) {
        super(message);
    }

    public FileUpLoadIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUpLoadIOException(Throwable cause) {
        super(cause);
    }

    protected FileUpLoadIOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
