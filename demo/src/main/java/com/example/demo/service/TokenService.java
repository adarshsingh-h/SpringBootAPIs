package com.example.demo.service;
import java.util.Date;
import javax.crypto.SecretKey;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
  private static final Logger log = LoggerFactory.getLogger(TokenService.class);
  private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

  public String getTokenForUser(String username) {
    long expirationTime = 1000 * 60 * 60; //1hour
    return Jwts.builder()
      .setSubject(username)
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
      .signWith(SECRET_KEY)
      .compact();
  }

  public boolean isTokenValid(String token) {
    try {
      Jwts.parserBuilder()
        .setSigningKey(SECRET_KEY)
        .build()
        .parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      log.error("Error API: ", e);
      return false;
    }
  }
}
