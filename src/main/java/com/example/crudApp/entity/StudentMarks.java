package com.example.crudApp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class StudentMarks extends BaseEntity{
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private long id;
 private Integer score;
 private LocalDate exam_date;
 @ManyToOne
 @JoinColumn(name = "student_id")
    private Student student;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public LocalDate getExam_date() {
        return exam_date;
    }

    public void setExam_date(LocalDate exam_date) {
        this.exam_date = exam_date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
