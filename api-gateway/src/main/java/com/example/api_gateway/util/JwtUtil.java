package com.example.api_gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.nio.charset.StandardCharsets;

public class JwtUtil {

    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkey123456";

    public static Claims getClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(
                        SECRET.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    public static boolean validate(String token) {

        try {

            getClaims(token);

            return true;

        } catch (Exception e) {

            return false;

        }
    }
}