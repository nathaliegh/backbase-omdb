package com.backbase.omdb.security.service;

import com.backbase.omdb.security.model.User;
import com.backbase.omdb.security.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public String login(User user) {
        authenticate(user);
        return jwtTokenUtil.generateToken(user);
    }

    private void authenticate(User user) {
        Objects.requireNonNull(user.getUsername());
        Objects.requireNonNull(user.getPassword());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (DisabledException e) {
            throw new DisabledException("Your account is deactivated");
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("The username or password that you entered is not valid. Try typing it again.");
        }
    }
}
