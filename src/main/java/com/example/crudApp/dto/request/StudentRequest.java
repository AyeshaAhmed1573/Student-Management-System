package com.example.crudApp.dto.request;

public class StudentRequest {
    public record Create( String name, String email, Integer age, Integer rollno, String subject, Long studentProfileId, Long studentDepartmentId){

    };
    public record Get(Long id){

    };
    public record Delete(Long id){

    };
    public record Update(Long id,String name, String email, Integer age, Integer rollno, String subject, Long studentProfileId, Long studentDepartmentId){}

}
//        Long profileID , Long departmentId
