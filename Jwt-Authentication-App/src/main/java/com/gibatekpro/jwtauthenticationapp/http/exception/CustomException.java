package com.gibatekpro.jwtauthenticationapp.http.exception;

/**
 *  This class represents the custom exception that will be thrown
 *
 *  It extends {@link RuntimeException}
 * */

public class CustomException extends RuntimeException{

    private String path;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    protected CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CustomException(String message, String requestURI) {
        super(message);
        this.path = requestURI;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
