package com.backbase.omdb.security.controller;


import com.backbase.omdb.security.payload.LoginPayload;
import com.backbase.omdb.security.service.AuthService;
import com.backbase.omdb.security.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@RequiredArgsConstructor
@Api(value = "Login", tags = "Authentication")
public class AuthController {

    private static final String NAME_ATTRIBUT_TOKEN = "token";


    private final AuthService authService;
    private final UserService userService;


    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<?> login(@Valid @RequestBody LoginPayload login) {
        var user = userService.findUserFromLogin(login.getUsername());
        if(Optional.ofNullable(user).isEmpty()) user = userService.saveUserFromLogin(login);
        String token = authService.login(user);
        return ResponseEntity.status(HttpStatus.OK)
                .header(NAME_ATTRIBUT_TOKEN, token)
                .contentType(APPLICATION_JSON)
                .body(user);
    }

}
