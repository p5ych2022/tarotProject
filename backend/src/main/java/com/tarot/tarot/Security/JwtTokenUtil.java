package com.tarot.tarot.Security;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;

import static io.jsonwebtoken.Jwts.*;

public class JwtTokenUtil {

    static Dotenv dotenv = Dotenv.load();
    static String jwtKey = dotenv.get("JWT_SECRET_KEY");

    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(jwtKey.getBytes());
    private static final long EXPIRATION_TIME = 864000000; // 10 days


    // Method to generate a JWT token with a username subject
    public static String generateToken(String username) {
        return builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();
    }

    // Method to validate the token and retrieve the subject (username)
    public static String validateToken(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public static boolean isTokenExpired(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration().before(new Date());
    }
}
