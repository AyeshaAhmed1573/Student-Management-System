package com.example.crudApp.service.services;

import com.example.crudApp.dto.request.AuthRequest;
import com.example.crudApp.dto.response.AuthResponse;

public interface AuthService {
AuthResponse.Login Login(AuthRequest.Login req);
AuthResponse.Register Register(AuthRequest.Register req);

}
