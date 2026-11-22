package com.example.crudApp.repository.repositorycustom;

import com.example.crudApp.dto.response.DepartmentCount;

import java.util.List;

public interface StudentDepartmentRepositoryCustom {
    List<DepartmentCount> countStudentsPerDepartment();
}