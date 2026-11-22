package com.example.crudApp.repository;

import com.example.crudApp.entity.Student;
import com.example.crudApp.repository.repositorycustom.StudentRepositoryCustom;
import com.example.crudApp.repository.repositoryimpl.StudentRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> , StudentRepositoryCustom {
    boolean existsByStudentDepartmentId(Long department_id);
    boolean existsByStudentProfileId(Long profileId);
    int countStudentsByDepartment(Long departmentId);



}
