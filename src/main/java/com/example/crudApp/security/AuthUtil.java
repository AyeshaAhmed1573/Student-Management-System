package com.example.crudApp.security;

import com.example.crudApp.entity.AppUser;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component

public class AuthUtil {
    @Value("${jwt.secretkey}")
    private String jwtsecretkey;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtsecretkey.getBytes(StandardCharsets.UTF_8));

    }
    public String generateAccessToken(AppUser user){
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userId", user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date (System.currentTimeMillis()+1000*60*10))
                .signWith(getSecretKey())
                .compact();




    }
    public String getUsernameFromToken(String token){
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }


}
