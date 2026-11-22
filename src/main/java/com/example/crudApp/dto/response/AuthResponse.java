package com.example.crudApp.dto.response;

public class AuthResponse {
    public record Login( String token,Long id,String username){}
    public record Register(String token,Long id,String username){}
}
