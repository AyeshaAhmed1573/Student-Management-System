package com.example.crudApp.dto.request;

public class AuthRequest {
    public record Login(String username,String password){}
    public record Register(String username, String password){}
}
