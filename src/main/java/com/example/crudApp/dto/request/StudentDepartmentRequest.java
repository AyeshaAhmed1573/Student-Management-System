package com.example.crudApp.dto.request;

public class StudentDepartmentRequest {
    public record Create(String name){};
    public record Get(Long id){};
    public record Update( Long id, String name){};
    public record  Delete(Long id){}


}
