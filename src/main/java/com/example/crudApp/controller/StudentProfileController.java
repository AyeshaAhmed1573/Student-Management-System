package com.example.crudApp.controller;




import com.example.crudApp.dto.response.StudentProfileResponse;
import com.example.crudApp.dto.request.StudentProfileRequest;
import com.example.crudApp.service.services.StudentProfileService;
import com.example.crudApp.service.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/profile")
public class StudentProfileController {
    private StudentProfileService studentProfileService;

    public StudentProfileController(StudentProfileService studentProfileService) {
        this.studentProfileService = studentProfileService;
    }

    @PostMapping("/create")
    public ResponseEntity<StudentProfileResponse.Create> Create(@RequestBody StudentProfileRequest.Create req) {
        StudentProfileResponse.Create createdStudent = studentProfileService.Create(req);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    @PostMapping ("/get")
    public ResponseEntity<StudentProfileResponse.Get> getById(@RequestBody  StudentProfileRequest.Get req ) {
        StudentProfileResponse.Get respStudent = studentProfileService.getById(req.id());
        if (respStudent == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(respStudent);
    }

    @GetMapping  ("/getall")
    public ResponseEntity<List<StudentProfileResponse.Get>> getAll() {
        List<StudentProfileResponse.Get> respStudent = studentProfileService.getAll();
        if (respStudent == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(respStudent);


    }

    @PostMapping("/update")
    public ResponseEntity<StudentProfileResponse.Update> updateById(@RequestBody StudentProfileRequest.Update req) {
        StudentProfileResponse.Update updatedStudent = studentProfileService.updateById(req);
        if (updatedStudent == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedStudent);
    }
    @PostMapping("/delete")
    public ResponseEntity<StudentProfileResponse.Delete> deleteById(@RequestBody StudentProfileRequest.Delete req) {
        StudentProfileResponse.Delete res = studentProfileService.deleteById(req.id());
        if (res == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new StudentProfileResponse.Delete("Profile not found"));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body( new StudentProfileResponse.Delete("Student deleted"));
    }
}