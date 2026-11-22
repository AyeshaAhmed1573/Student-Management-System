package com.example.crudApp.service.services;

import com.example.crudApp.dto.request.StudentProfileRequest;
import com.example.crudApp.dto.response.StudentProfileResponse;

import java.util.List;

public interface StudentProfileService {
    StudentProfileResponse.Create Create(StudentProfileRequest.Create req);
    List<StudentProfileResponse.Get> getAll();
    StudentProfileResponse.Get getById(Long id);
    StudentProfileResponse.Update updateById(StudentProfileRequest.Update req);
    StudentProfileResponse.Delete deleteById(Long id);

}
