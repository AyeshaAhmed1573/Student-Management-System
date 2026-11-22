package com.example.crudApp.dto.response;

public class StudentProfileResponse {
    public record Create(Long id,String phone , String address, String guardianname){}
    public record Get(Long id,String phone , String address, String guardianname){}
    public record Update(Long id,String phone , String address, String guardianname){}
    public record Delete (String message){}
}
