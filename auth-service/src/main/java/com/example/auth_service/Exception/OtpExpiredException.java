package com.example.auth_service.Exception;

public class OtpExpiredException extends RuntimeException{

    public OtpExpiredException(String message){

        super(message);

    }

}
