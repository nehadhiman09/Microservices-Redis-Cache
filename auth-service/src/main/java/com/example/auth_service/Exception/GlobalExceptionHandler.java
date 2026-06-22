package com.example.auth_service.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionHandler {
    @ExceptionHandler(EmailNotFoundException.class)

    public ResponseEntity<?> emailNotFound(EmailNotFoundException ex){

        Map<String,Object> map = new HashMap<>();

        map.put("time", LocalDateTime.now());

        map.put("message", ex.getMessage());

        map.put("status",404);

        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(InvalidOtpException.class)

    public ResponseEntity<?> invalidOtp(InvalidOtpException ex){

        Map<String,Object> map = new HashMap<>();

        map.put("time",LocalDateTime.now());

        map.put("message",ex.getMessage());

        map.put("status",401);

        return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(OtpExpiredException.class)

    public ResponseEntity<?> otpExpired(OtpExpiredException ex){

        Map<String,Object> map = new HashMap<>();

        map.put("time",LocalDateTime.now());

        map.put("message",ex.getMessage());

        map.put("status",400);

        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST

        );

    }
}
