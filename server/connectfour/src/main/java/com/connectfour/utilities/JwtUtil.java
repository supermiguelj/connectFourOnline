package com.connectfour.utilities;

import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import com.connectfour.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
    // Sshhhh! Don't tell anyone!
    private static final String SECRET_KEY = "JkNzGBVkauMG0dO08NcWTapJyAYUHfiC";
    // Session lasts an hour
    private static final int SESSION_TIME = 30;

    public static SecretKey getKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public static String generateToken(User user) {
        return Jwts.builder()
                    .subject(user.getUsername())
                    .issuedAt(Date.from(Instant.now()))
                    .expiration(Date.from(Instant.now().plusSeconds(SESSION_TIME)))
                    .signWith(JwtUtil.getKey(), SignatureAlgorithm.HS256)
                    .compact();
    }

    public static boolean validateToken(String token) {
        try {
                Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token);
                return true;
            } catch (JwtException | IllegalArgumentException e) {
                return false;
            }
    }

    public static String extractUsername(String token) {
        return Jwts.parser()
        .setSigningKey(getKey())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
    }

    public static int getSessionTime() {
        return SESSION_TIME;
    }
}
