package com.example.crudApp.dto.response;

public class StudentResponse {

    public record Create(Long id, String name, String email, Integer age, Integer rollno,
                         String subject, Long studentProfileId, Long studentDepartmentId) {}

    public record Get(Long id, String name, String email, Integer age, Integer rollno,
                      String subject, Long studentProfileId, Long studentDepartmentId) {}

    public record Update(Long id, String name, String email, Integer age, Integer rollno,
                         String subject, Long studentProfileId, Long studentDepartmentId) {}

    public record Delete(String message) {}

    public record DepartmentCount(Long departmentId, Integer total) {}
}