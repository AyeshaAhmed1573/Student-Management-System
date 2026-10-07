package com.example.crudApp.service;

import com.example.crudApp.entity.Student;
import com.example.crudApp.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public Student createStudent(Student studentReq){
        Student studentresp=studentRepository.save(studentReq);
        return studentresp;
    }
    public Student getStudent(Long id){
        Optional<Student> studentresp=studentRepository.findById(id);
        if (studentresp.isPresent()) {
            return studentresp.get();
        }
        return null;

        }

    public List <Student> getAllStudent(){
        List<Student> studentresp=studentRepository.findAll();
        return studentresp;

    }
    public Student updateStudent(Long id, Student studentReq) {
        Optional<Student> existing = studentRepository.findById(id);
        if (existing.isPresent()) {
            Student student = existing.get();
            student.setName(studentReq.getName());
            student.setSubject(studentReq.getSubject());
            return studentRepository.save(student);
        }
        return null;
    }
    public boolean deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
