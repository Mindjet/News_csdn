package com.mindjet.com.news_csdn.Bean;

/**
 * @author Mindjet
 * @date 2016/6/29
 */
public class CommonException extends Exception {

    public CommonException() {
        super();
    }

    public CommonException(Throwable cause) {
        super(cause);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonException(String message) {
        super(message);
    }
}
