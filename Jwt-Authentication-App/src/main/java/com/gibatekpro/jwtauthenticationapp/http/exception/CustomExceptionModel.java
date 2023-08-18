package com.gibatekpro.jwtauthenticationapp.http.exception;

import java.util.Date;

/**
 *  This class represents the model of the data that will be
 *  returned as an exception. It will look like this in JSON FORMAT
 *
 *  {
 *      "timeStamp": "2023-08-10 21:13:27"
 *      "status": 404,
 *      "message": "The message",
 *  }
 *
 * */
public class CustomExceptionModel {

    private Date timeStamp;

    private int status;

    private String error;

    private String message;

    private String path;


    CustomExceptionModel(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
