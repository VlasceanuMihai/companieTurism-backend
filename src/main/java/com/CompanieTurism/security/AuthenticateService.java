package com.CompanieTurism.security;

import com.CompanieTurism.security.jwt.JwtTokenUtil;
import com.CompanieTurism.security.request.AuthenticationRequest;
import com.CompanieTurism.security.response.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Service
public class AuthenticateService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthenticateService(AuthenticationManager authenticationManager,
                               UserDetailsService userDetailsService,
                               JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("INVALID_CREDENTIALS", e);
        }
    }

    public AuthenticationResponse generateToken(AuthenticationRequest request) {
        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

        final String token = this.jwtTokenUtil.generateToken(userDetails);

        return new AuthenticationResponse(token);
    }

    public ResponseEntity<Object> refreshAndGetAuthenticationToken(HttpServletRequest request, String tokenHeader, Integer bounded) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(bounded);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserPrincipal userPrincipal = (UserPrincipal) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
            String refreshedToken = this.jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new AuthenticationResponse(refreshedToken));
        }else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
