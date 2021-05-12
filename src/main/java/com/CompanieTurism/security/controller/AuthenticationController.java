package com.CompanieTurism.security.controller;

import com.CompanieTurism.security.AuthenticateService;
import com.CompanieTurism.security.AuthenticationException;
import com.CompanieTurism.security.request.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    private static final Integer BOUNDED = 7;

    @Value("${jwt.http.request.header}")
    private String tokenHeader;

    private final AuthenticateService authenticateService;

    @Autowired
    public AuthenticationController(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @PostMapping(value = "${jwt.get.token.uri}")
    public ResponseEntity<Object> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws AuthenticationException {
        this.authenticateService.authenticate(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(this.authenticateService.generateToken(request));
    }

    @GetMapping(value = "${jwt.refresh.token.uri}")
    public ResponseEntity<Object> refreshAndGetAuthenticationToken(HttpServletRequest request) {
         return this.authenticateService.refreshAndGetAuthenticationToken(request, tokenHeader, BOUNDED);
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}
