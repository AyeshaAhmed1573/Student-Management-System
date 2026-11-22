package com.example.crudApp.controller;

import com.example.crudApp.dto.request.AuthRequest;
import com.example.crudApp.dto.response.AuthResponse;
import com.example.crudApp.service.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping("/api/auth")
public class AuthController
{
    private AuthService authService;

    AuthController(AuthService authService){
        this.authService=authService;
    }

@PostMapping("/login")
    public ResponseEntity<AuthResponse.Login> Login(@RequestBody AuthRequest.Login req){
    return ResponseEntity.ok(authService.Login(req));
}
@PostMapping("/signup")
    public ResponseEntity<AuthResponse.Register> Register(@RequestBody AuthRequest.Register req){
        return ResponseEntity.ok(authService.Register(req));
}

}
