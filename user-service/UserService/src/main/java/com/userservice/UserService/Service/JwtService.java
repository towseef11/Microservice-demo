package com.userservice.UserService.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.userservice.UserService.Model.User;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;

@Service 
public class JwtService {

    @Value("${jwt.secret-key}")
    private String secretkey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(secretkey.getBytes(StandardCharsets.UTF_8));
    }
    
    public String generateToken(User user){
        return Jwts.builder()
                  .subject(user.getEmail())
                  .claim("role", user.getRole())
                  .issuedAt(new Date())
                  .expiration(new Date(System.currentTimeMillis() +jwtExpiration))
                  .signWith(getSigningKey())
                  .compact();
    }

    public boolean validate(String token){
            try{
                Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
                return true;    
            }catch(Exception e){
                return  false;
            }
    }

    public String extractMail(String token){
        return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
    }

    public String extractRole(String token){
        return Jwts.parser()
                   .verifyWith(getSigningKey())
                   .build()
                   .parseSignedClaims(token)
                   .getPayload()
                   .get("role",String.class);
    }

}
