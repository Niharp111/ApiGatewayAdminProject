package com.cts.apigateway.config.exception;

/**
 * Created by cts1 on 20/3/14.
 */
public class ConfigSystemException extends Exception {

    private String message;

    public ConfigSystemException(String message) {
        super(message);
        this.message = message;
    }

    public ConfigSystemException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
