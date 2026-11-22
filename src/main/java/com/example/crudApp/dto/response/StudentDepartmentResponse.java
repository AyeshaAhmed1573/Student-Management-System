package com.example.crudApp.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;

public class StudentDepartmentResponse {
    public record Create(Long id, String name, LocalDateTime createdAt) {};
    public record Get(Long id,String name, LocalDateTime createdAt)implements Serializable{};
    public record Update( Long id, String name, LocalDateTime createdAt){};
    public record  Delete(String message){}
}
