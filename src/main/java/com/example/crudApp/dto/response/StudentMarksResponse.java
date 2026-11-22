package com.example.crudApp.dto.response;

import java.time.LocalDate;

public class StudentMarksResponse {
    public record Create(Long id,Integer score, LocalDate exam_date, Long studentId, String studentName){}
    public record Get(Long id,Integer score, LocalDate exam_date, Long studentId, String studentName){}
    public record GetByStudent(Long id,Integer score, LocalDate exam_date, Long studentId, String studentName){}
    public record Update(Long id,Integer score, LocalDate exam_date,Long studentId, String studentName){}
    public record Delete(String message){}
}
