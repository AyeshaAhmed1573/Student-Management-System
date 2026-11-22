package com.example.crudApp.repository;

import com.example.crudApp.dto.response.DepartmentCount;
import com.example.crudApp.entity.StudentDepartment;
import com.example.crudApp.repository.repositorycustom.StudentDepartmentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentDepartmentRepository extends JpaRepository<StudentDepartment,Long>, StudentDepartmentRepositoryCustom {
    List<DepartmentCount> countStudentsPerDepartment();

}
