package com.example.crudApp.repository;

import com.example.crudApp.entity.StudentMarks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentMarksRepository extends JpaRepository<StudentMarks,Long> {
}
