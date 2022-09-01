package com.platzi.market.web.controller;

import com.platzi.market.domain.dto.AuthenticationReponse;
import com.platzi.market.domain.dto.AuthenticationRequest;
import com.platzi.market.domain.service.PlatziUserDetailsService;
import com.platzi.market.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PlatziUserDetailsService userDetailsService;
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationReponse> createToken(@RequestBody AuthenticationRequest request){

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);
            return new ResponseEntity<>(new AuthenticationReponse(jwt), HttpStatus.OK);
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
