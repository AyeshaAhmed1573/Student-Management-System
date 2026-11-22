package com.example.crudApp.service.services;

import com.example.crudApp.dto.request.StudentRequest;
import com.example.crudApp.dto.response.StudentResponse;
import java.util.List;


public interface StudentService {
    StudentResponse.Create Create(StudentRequest.Create req);
    List<StudentResponse.Get> getAll();
    StudentResponse.Get getById(Long id);
    StudentResponse.Update updateById(StudentRequest.Update req);
    boolean deleteById(Long id);
    StudentResponse.DepartmentCount countByDepartment(Long departmentId);
}


