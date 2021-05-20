package com.javohir.apppayment.controller;

import com.javohir.apppayment.payload.LoginDto;
import com.javohir.apppayment.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public HttpEntity<?> logInToSystem(@RequestBody LoginDto loginDto) {

        try {
            //shunaqa tizim odami bormi tekshirish
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

            String token = jwtProvider.generateToken(loginDto.getUsername());
            return ResponseEntity.ok(token);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Login yoki Parolz notogri!");
        }

    }
}
