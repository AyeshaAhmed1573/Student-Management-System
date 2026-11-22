package com.example.crudApp.service.services;

import com.example.crudApp.dto.request.StudentMarksRequest;
import com.example.crudApp.dto.response.StudentMarksResponse;

import java.util.List;

public interface StudentMarksService {
    StudentMarksResponse.Create Create(StudentMarksRequest.Create req);
    List<StudentMarksResponse.Get> getAll();
    StudentMarksResponse.Get getById(Long id);
    StudentMarksResponse.Update updateById(StudentMarksRequest.Update req);
    StudentMarksResponse.Delete deleteById(Long id);

}
