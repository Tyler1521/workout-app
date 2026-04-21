package org.example.testing.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.testing.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public String createAuthenticationToken(HttpServletRequest request) {
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Basic ")) {
                throw new RuntimeException("Missing or invalid Authorization header");
            }
            String base64Credentials = authHeader.substring("Basic ".length());
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded, StandardCharsets.UTF_8);
            final String[] values = credentials.split(":", 2);

            String username = values[0];
            String password = values[1];

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (userDetails != null && userDetails.getPassword().equals("{noop}" + password)) {
                return jwtUtil.generateToken(username);
            }
            throw new RuntimeException("Invalid credentials");
        }  catch (Exception e){
            log.error("Failed to Authenticate: {}", e.getMessage(), e);
            throw new RuntimeException("Auth error");
        }

    }
}