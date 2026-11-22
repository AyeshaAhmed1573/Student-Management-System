package com.example.crudApp.controller;


import com.example.crudApp.dto.request.StudentRequest;
import com.example.crudApp.dto.response.StudentResponse;

import com.example.crudApp.service.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<StudentResponse.Create> createStudent(@RequestBody StudentRequest.Create req) {
        StudentResponse.Create createdStudent = studentService.Create(req);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    @PostMapping ("/get")
    public ResponseEntity<StudentResponse.Get> getStudent(@RequestBody  StudentRequest.Get req ) {
        StudentResponse.Get respStudent = studentService.getById(req.id());
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
    public ResponseEntity<List<StudentResponse.Get>> getAllStudent() {
        List<StudentResponse.Get> respStudent = studentService.getAll();
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
    public ResponseEntity<StudentResponse.Update> updateStudent(@RequestBody StudentRequest.Update req) {
        StudentResponse.Update updatedStudent = studentService.updateById(req);
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
    public ResponseEntity<StudentResponse.Delete> deleteStudent(@RequestBody StudentRequest.Delete req) {
        boolean deleted = studentService.deleteById(req.id());
        if (!deleted) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body( new StudentResponse.Delete("Student not found"));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body( new StudentResponse.Delete("Student deleted"));
    }
    @PostMapping("/count-by-department")
    public ResponseEntity<StudentResponse.DepartmentCount> countByDepartment(@RequestBody StudentRequest.Get req) {
        return ResponseEntity.ok(studentService.countByDepartment(req.id()));
    }
}