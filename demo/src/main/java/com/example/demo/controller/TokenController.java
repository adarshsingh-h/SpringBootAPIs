package com.example.demo.controller;

import com.example.demo.service.TokenService;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TokenController {

  @Autowired
  private TokenService tokenService;

  @GetMapping("/token")
  public ResponseEntity<?> getToken(@RequestParam String username) {
    String token = tokenService.getTokenForUser(username);
    return ResponseEntity.ok(Collections.singletonMap("token", token));
  }

  @GetMapping("/validate")
  public String validateToken(@RequestParam String token) {
    boolean isValid = tokenService.isTokenValid(token);
    return isValid ? "Token is valid ✅" : "Token is invalid ❌";
  }
}
