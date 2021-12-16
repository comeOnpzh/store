package com.example.store.controller.ex;

/**
 * Created by pengzh5 Cotter on 2021/12/13.
 * 控制层文件处理异常的基类
 */
public class FileUpLoadException extends RuntimeException{
    public FileUpLoadException() {
        super();
    }

    public FileUpLoadException(String message) {
        super(message);
    }

    public FileUpLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUpLoadException(Throwable cause) {
        super(cause);
    }

    protected FileUpLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
