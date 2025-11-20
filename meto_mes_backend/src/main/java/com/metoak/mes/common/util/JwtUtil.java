package com.metoak.mes.common.util;

import com.metoak.mes.common.MOException;
import com.metoak.mes.enums.ResultCodeEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.SecretKey;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JwtUtil {

    private static final long tokenExpiration = 60 * 60 * 100000L;
    private static SecretKey tokenSignKey = Keys.hmacShaKeyFor("M0PKKI6pYGVWWfDZw90a0lTpGYX1d4AQ".getBytes());

    @Autowired
    private CheckUtil checkUtil;


    public static String createToken(Long userId, String username) {

        return Jwts.builder().
                setSubject("USER_INFO").
                setExpiration(new Date(System.currentTimeMillis() + tokenExpiration)).
                claim("userId", userId).
                claim("username", username).
                signWith(tokenSignKey).
                compact();
    }

    public static String calculateTokenExpireDate() {
        Date expireDate = new Date(System.currentTimeMillis() + tokenExpiration * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        return sdf.format(expireDate);
    }

    public static Claims parseToken(String token) {

        if (token==null){
            throw new MOException(ResultCodeEnum.TOEKN_NULL);
        }

        String prefix = "Bearer ";

        if (token.startsWith(prefix)) {
            token = token.substring(prefix.length());
        }

        try{
            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(tokenSignKey).build();
            return jwtParser.parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e){
            return e.getClaims();
        } catch (JwtException e){
            throw new MOException(ResultCodeEnum.TOKEN_INVALID);
        }
    }
}