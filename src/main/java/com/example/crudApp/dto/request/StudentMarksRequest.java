package com.example.crudApp.dto.request;

import java.time.LocalDate;

public class StudentMarksRequest {
    public record Create(Integer score, LocalDate exam_date,Long studentId, String studentName){}
    public record Get(Long id){}
    public record GetByStudent(Long studentId){}
    public record Update(Long id,Integer score, LocalDate exam_date,Long studentId, String studentName){}
    public record Delete(Long id){}
}
