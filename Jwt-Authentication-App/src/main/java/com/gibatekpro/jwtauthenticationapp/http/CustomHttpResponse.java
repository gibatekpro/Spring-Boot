package com.gibatekpro.jwtauthenticationapp.http;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CustomHttpResponse {

    protected String timeStamp;

    protected int statusCode;

    protected HttpStatus status;

    protected  String reason;

    protected String message;

    protected String developerMessage;

    protected Map<?,?> data;

}

