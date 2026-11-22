package com.example.crudApp.service.serviceimpl;

import com.example.crudApp.dto.request.AuthRequest;
import com.example.crudApp.dto.response.AuthResponse;
import com.example.crudApp.entity.AppUser;
import com.example.crudApp.repository.AppUserRespository;
import com.example.crudApp.security.AuthUtil;
import com.example.crudApp.service.services.AuthService;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service

public class AuthServiceImpl implements AuthService {
    private static final Logger log= LoggerFactory.getLogger(AuthServiceImpl.class);
    private AuthenticationManager authenticationManager;
    private AppUserRespository appUserRespository;
    private final AuthUtil authUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager,AppUserRespository appUserRespository,AuthUtil authUtil,PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.appUserRespository=appUserRespository;
        this.authUtil=authUtil;
        this.passwordEncoder=passwordEncoder;
    }
    @Override
    public AuthResponse.Login Login(AuthRequest.Login req) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.username(), req.password()));
        }catch (BadCredentialsException e){
            log.warn("Login failed: username={}", req.username());
            throw e;

        }
            AppUser user = appUserRespository.findByUsername(req.username()).orElseThrow();

                String token =authUtil.generateAccessToken(user);
       return new AuthResponse.Login(token, user.getId(), user.getUsername());
    }
    @Override
    public AuthResponse.Register Register(AuthRequest.Register req){
        if( appUserRespository.existsByUsername(req.username()))
        {
            log.warn("Register rejected, username taken: {}", req.username());
            throw new IllegalArgumentException("user already exist");
        }

        AppUser user= new AppUser();

        user.setUsername(req.username());
        user.setPassword(passwordEncoder.encode(req.password()));
        user= appUserRespository.save(user);
        String token=authUtil.generateAccessToken(user);
        log.info("user successfully made:{}",user.getUsername());
        return new AuthResponse.Register(token, user.getId(), user.getUsername());

    }



}
