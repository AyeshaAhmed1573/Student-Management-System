package com.example.crudApp.repository.repositorycustom;

import com.example.crudApp.entity.Student;

import java.util.List;

public interface StudentRepositoryCustom {
    boolean existsByStudentDepartmentId(Long studentDepartmentId);
    boolean existsByStudentProfileId(Long studentProfileId);
    int countStudentsByDepartment(Long departmentId);

}
