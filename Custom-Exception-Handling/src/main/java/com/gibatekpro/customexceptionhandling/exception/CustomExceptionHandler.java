package com.gibatekpro.customexceptionhandling.exception;

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
public class CustomExceptionHandler {

    //This method will handle a specific type of exception you throw
    @ExceptionHandler
    public ResponseEntity<CustomExceptionModel> handleException(CustomException customException){

        CustomExceptionModel exceptionModel = new CustomExceptionModel();

        exceptionModel.setTimeStamp(new Date());
        exceptionModel.setStatus(HttpStatus.NOT_FOUND.value());// Place your custom status here exception here
        exceptionModel.setError(HttpStatus.NOT_FOUND.toString());
        exceptionModel.setMessage(customException.getMessage());
        exceptionModel.setPath(customException.getPath());

        return new ResponseEntity<>(exceptionModel, HttpStatus.NOT_FOUND);
    }

    //This method will handle any Exception you did not define
    @ExceptionHandler
    public ResponseEntity<CustomExceptionModel> handleException(Exception e){

        CustomExceptionModel exceptionModel = new CustomExceptionModel();

        exceptionModel.setMessage(e.getMessage());
        exceptionModel.setStatus(HttpStatus.BAD_REQUEST.value());// Place your custom status here exception here
        exceptionModel.setTimeStamp(new Date());

        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }

}
