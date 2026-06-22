package com.example.auth_service.Service;

import com.example.auth_service.dto.JwtResponse;
import com.example.auth_service.dto.SendOtpRequest;
import com.example.auth_service.dto.VerifyOtpRequest;
import com.example.auth_service.feign.UserServiceClient;
import com.example.auth_service.util.JwtUtil;
import com.example.auth_service.util.OtpGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AuthService {

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SmsService smsService;

    public String sendOtp(SendOtpRequest request) {
        String otp = OtpGenerator.generateOtp();
        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            Boolean exists = userServiceClient.checkEmailExists(request.getEmail());
            if (exists == null || !exists) {
                throw new RuntimeException("Email not registered");
            }
            String redisKey = "OTP:EMAIL:" + request.getEmail();
            redisTemplate.opsForValue().set( redisKey, otp, 1, TimeUnit.MINUTES );
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(request.getEmail());
            message.setSubject("Login OTP");
            message.setText("Your OTP is: " + otp);
            mailSender.send(message);
            return "OTP sent successfully to email."; }
         if (request.getMobile() != null && !request.getMobile().isBlank()) {
             String redisKey = "OTP:MOBILE:" + request.getMobile();
             redisTemplate.opsForValue().set( redisKey, otp, 1, TimeUnit.MINUTES );
             smsService.sendOtp(request.getMobile(), otp);
             return "OTP sent successfully to mobile."; }
         throw new RuntimeException("Please provide either email or mobile.");
    }
    public JwtResponse verifyOtp(VerifyOtpRequest request) {
        String redisKey;
        String subject;
        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            redisKey = "OTP:EMAIL:" + request.getEmail(); subject = request.getEmail(); }
        else if (request.getMobile() != null && !request.getMobile().isBlank()) {
            redisKey = "OTP:MOBILE:" + request.getMobile(); subject = request.getMobile(); }
        else {
            throw new RuntimeException("Please provide either email or mobile."); }
        Object redisOtp = redisTemplate.opsForValue().get(redisKey);
        if (redisOtp == null) {
            throw new RuntimeException("OTP Expired"); }
        if (!redisOtp.toString().equals(request.getOtp())) {
            throw new RuntimeException("Invalid OTP"); }
        redisTemplate.delete(redisKey);
        String token = JwtUtil.generateToken(subject);
        return new JwtResponse(token); }

        }