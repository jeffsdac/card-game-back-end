package br.com.cardgame.jeff.security;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTGenerator {
    
    public String generateToken(Authentication auth){
        String user = auth.getName();
        Date currentDate = new Date();
        Date expiredDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);
        
        String token = Jwts.builder()
        .setSubject(user)
        .setIssuedAt(currentDate)
        .setExpiration(expiredDate)
        .signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET)
        .compact();

        return token;
    }


    public String getUsernameFromJwt (String token) {
        Claims claims = Jwts.parser()
                        .setSigningKey(SecurityConstants.JWT_SECRET)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

        return claims.getSubject();
    }

    public boolean validate (String token){
        try{
            Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).build().parseClaimsJws(token);
            return true;
        } catch (Exception exc){
            throw new AuthenticationCredentialsNotFoundException("JWT WAS EXPIRED, OR INCORRECT");
        }
    }
}
