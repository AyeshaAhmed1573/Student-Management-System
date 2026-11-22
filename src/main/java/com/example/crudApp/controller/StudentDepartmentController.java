package com.example.crudApp.controller;




import com.example.crudApp.dto.response.StudentDepartmentResponse;
import com.example.crudApp.dto.request.StudentDepartmentRequest;
import com.example.crudApp.service.services.StudentDepartmentService;
import com.example.crudApp.service.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/department")
public class StudentDepartmentController {
    private StudentDepartmentService studentDepartmentService;

    public StudentDepartmentController(StudentDepartmentService studentDepartmentService) {
        this.studentDepartmentService = studentDepartmentService;
    }

    @PostMapping("/create")
    public ResponseEntity<StudentDepartmentResponse.Create> Create(@RequestBody StudentDepartmentRequest.Create req) {
        StudentDepartmentResponse.Create createdStudent = studentDepartmentService.Create(req);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    @PostMapping ("/get")
    public ResponseEntity<StudentDepartmentResponse.Get> getById(@RequestBody  StudentDepartmentRequest.Get req ) {
        StudentDepartmentResponse.Get respStudent = studentDepartmentService.getById(req.id());
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
    public ResponseEntity<List<StudentDepartmentResponse.Get>> getAll() {
        List<StudentDepartmentResponse.Get> respStudent = studentDepartmentService.getAll();
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
    public ResponseEntity<StudentDepartmentResponse.Update> updateById(@RequestBody StudentDepartmentRequest.Update req) {
        StudentDepartmentResponse.Update updatedStudent = studentDepartmentService.updateById(req);
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
    public ResponseEntity<StudentDepartmentResponse.Delete> deleteById(@RequestBody StudentDepartmentRequest.Delete req) {
        StudentDepartmentResponse.Delete res = studentDepartmentService.deleteById(req.id());
        if (res == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new StudentDepartmentResponse.Delete("Department not found"));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body( new StudentDepartmentResponse.Delete("Student deleted"));
    }
}