package com.example.crudApp.service.services;

import com.example.crudApp.dto.request.StudentDepartmentRequest;
import com.example.crudApp.dto.response.StudentDepartmentResponse;

import java.util.List;

public interface StudentDepartmentService {
    StudentDepartmentResponse.Create Create(StudentDepartmentRequest.Create req);
  List<StudentDepartmentResponse.Get> getAll();
    StudentDepartmentResponse.Get getById(Long id);
    StudentDepartmentResponse.Update updateById(StudentDepartmentRequest.Update req);
    StudentDepartmentResponse.Delete deleteById(Long id);


}
