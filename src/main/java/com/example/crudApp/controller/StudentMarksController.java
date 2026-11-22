package com.example.crudApp.controller;




import com.example.crudApp.dto.response.StudentMarksResponse;
import com.example.crudApp.dto.request.StudentMarksRequest;
import com.example.crudApp.service.services.StudentMarksService;
import com.example.crudApp.service.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/marks")
public class StudentMarksController {
    private StudentMarksService studentMarksService;

    public StudentMarksController(StudentMarksService studentMarksService) {
        this.studentMarksService = studentMarksService;
    }

    @PostMapping("/create")
    public ResponseEntity<StudentMarksResponse.Create> Create(@RequestBody StudentMarksRequest.Create req) {
        StudentMarksResponse.Create createdStudent = studentMarksService.Create(req);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    @PostMapping ("/get")
    public ResponseEntity<StudentMarksResponse.Get> getById(@RequestBody  StudentMarksRequest.Get req ) {
        StudentMarksResponse.Get respStudent = studentMarksService.getById(req.id());
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
    public ResponseEntity<List<StudentMarksResponse.Get>> getAll() {
        List<StudentMarksResponse.Get> respStudent = studentMarksService.getAll();
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
    public ResponseEntity<StudentMarksResponse.Update> updateById(@RequestBody StudentMarksRequest.Update req) {
        StudentMarksResponse.Update updatedStudent = studentMarksService.updateById(req);
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
    public ResponseEntity<StudentMarksResponse.Delete> deleteById(@RequestBody StudentMarksRequest.Delete req) {
        StudentMarksResponse.Delete res = studentMarksService.deleteById(req.id());
        if (res == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new StudentMarksResponse.Delete("Marks not found"));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body( new StudentMarksResponse.Delete("Student deleted"));
    }
}