package com.gibatekpro.jwtauthenticationapp.authentication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;


/**
 * The {@link ControllerAdvice} handles exceptions globally
 * To handle exceptions specific to certain controllers,
 * place the {@link ExceptionHandler} method in that controller class directly
 * */
@ControllerAdvice
public class AuthExceptionHandler {

    //This method will handle a specific type of exception you throw
    @ExceptionHandler
    public ResponseEntity<AuthExceptionModel> handleException(AuthException authException){

        AuthExceptionModel authExceptionModel = new AuthExceptionModel();

        authExceptionModel.setTimeStamp(new Date());
        authExceptionModel.setStatus(HttpStatus.BAD_REQUEST.value());// Place your custom status here exception here
        authExceptionModel.setError(HttpStatus.BAD_REQUEST.toString());
        authExceptionModel.setMessage(authException.getMessage());
        authExceptionModel.setPath(authException.getPath());

        return new ResponseEntity<>(authExceptionModel, HttpStatus.BAD_REQUEST);
    }

    //This method will handle any Exception you did not define
    @ExceptionHandler
    public ResponseEntity<AuthExceptionModel> handleException(Exception e){

        AuthExceptionModel authExceptionModel = new AuthExceptionModel();

        authExceptionModel.setMessage(e.getMessage());
        authExceptionModel.setStatus(HttpStatus.BAD_REQUEST.value());// Place your custom status here exception here
        authExceptionModel.setTimeStamp(new Date());

        return new ResponseEntity<>(authExceptionModel, HttpStatus.BAD_REQUEST);
    }

}
