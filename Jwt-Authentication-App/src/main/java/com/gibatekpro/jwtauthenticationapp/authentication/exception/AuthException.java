package com.gibatekpro.jwtauthenticationapp.authentication.exception;

/**
 *  This class represents the custom exception that will be thrown
 *
 *  It extends {@link RuntimeException}
 * */

public class AuthException extends RuntimeException{

    private String path;

    public AuthException(String message) {
        super(message);
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthException(Throwable cause) {
        super(cause);
    }

    protected AuthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AuthException(String message, String requestURI) {
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
