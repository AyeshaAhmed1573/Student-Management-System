package com.example.crudApp.dto.request;

public class StudentProfileRequest {
    public record Create(String phone , String address, String guardianname){}
    public record Get(Long id){}
    public record Update(Long id,String phone , String address, String guardianname){}
    public record Delete (Long id){}
}
