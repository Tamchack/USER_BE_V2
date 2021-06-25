package com.tamchack.tamchack.security.token;

import com.tamchack.tamchack.exception.ExpiredTokenException;
import com.tamchack.tamchack.exception.InvalidTokenException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;


@Component
public class JwtProvider {
    @Value("${auth.jwt.secret}")
    private String SECURITY_KEY;

    private String generateToken(Object data, Long expire, String type, String userType){

        long nowMillis = System.currentTimeMillis();

        JwtBuilder builder = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(nowMillis))
                .setHeaderParam("type", type)
                .setSubject(data.toString())
                .claim("userType", userType)
                .setExpiration(new Date(nowMillis + expire))
                .signWith(SignatureAlgorithm.HS256, SECURITY_KEY.getBytes());

        return builder.compact();
    }

    public String getAccessToken(Object data, String userType) {
        return generateToken(data, 1000L * 3600 * 24, "access_token", userType);
    }

    public String getRefreshToken(Object data, String userType) {
        return generateToken(data, 1000L * 3600 * 24 * 30, "refresh_token", userType);
    }

    public String parseToken(String token) throws ExpiredJwtException {
        String result;
        try {
            result = Jwts.parser().setSigningKey(SECURITY_KEY.getBytes()).parseClaimsJws(token).getBody().getSubject();
            if(!Jwts.parser().setSigningKey(SECURITY_KEY.getBytes()).parseClaimsJws(token).getBody().get("type").equals("access_token"))
                throw new InvalidTokenException();
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        } catch (MalformedJwtException e) {
            throw new InvalidTokenException();
        }
        return token;
    }

    public String resolveToken(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader("Authorization");
    }

    public boolean validToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECURITY_KEY)
                    .parseClaimsJws(token).getBody().getSubject();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUser(String token) {
        return Jwts.parser().setSigningKey(SECURITY_KEY)
                .parseClaimsJws(token).getBody().get("userType").equals("user");
    }

}
