package com.example.auth_service.Controller;

import com.example.auth_service.Service.AuthService;
import com.example.auth_service.dto.JwtResponse;
import com.example.auth_service.dto.SendOtpRequest;
import com.example.auth_service.dto.VerifyOtpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired

    private AuthService service;

    @PostMapping("/send-otp")

    public String sendOtp(

            @RequestBody
            SendOtpRequest request

    ){
        return service.sendOtp(request);

    }

    @PostMapping("/verify-otp")

    public JwtResponse verifyOtp(

            @RequestBody
            VerifyOtpRequest request

    ){

        return service.verifyOtp(request);

    }
    }

