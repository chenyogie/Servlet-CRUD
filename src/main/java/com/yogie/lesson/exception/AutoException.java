package com.yogie.lesson.exception;

/**
 * @program: BaseDemo
 * @Date: 2019/5/28 16:25
 * @Author: Chenyogie
 * @Description:
 */
public class AutoException extends RuntimeException {
    private String message;

    public AutoException(String message) {
        this.message = message;
    }

    public AutoException(String message, String message1) {
        super(message);
        this.message = message1;
    }

    public AutoException(String message, Throwable cause, String message1) {
        super(message, cause);
        this.message = message1;
    }

    public AutoException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }

    public AutoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
