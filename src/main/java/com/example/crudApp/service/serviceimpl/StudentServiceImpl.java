package com.example.crudApp.service.serviceimpl;

import com.example.crudApp.dto.request.StudentRequest;
import com.example.crudApp.dto.response.StudentResponse;
import com.example.crudApp.entity.Student;
import com.example.crudApp.entity.StudentDepartment;
import com.example.crudApp.entity.StudentProfile;
import com.example.crudApp.exception.ResourceNotFoundException;
import com.example.crudApp.repository.StudentDepartmentRepository;
import com.example.crudApp.repository.StudentProfileRepository;
import com.example.crudApp.repository.StudentRepository;
import com.example.crudApp.service.services.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final StudentDepartmentRepository studentDepartmentRepository;
    private final ModelMapper modelMapper;


    public  StudentServiceImpl(ModelMapper modelMapper,StudentRepository studentRepository , StudentProfileRepository studentProfileRepository,StudentDepartmentRepository studentDepartmentRepository) {
        this.studentProfileRepository = studentProfileRepository;
        this.studentDepartmentRepository= studentDepartmentRepository;
        this.studentRepository=studentRepository;
        this.modelMapper=modelMapper;

    }
    @Override
    public StudentResponse.Create Create(StudentRequest.Create studentReq){
//
          StudentDepartment dept= studentDepartmentRepository.findById(studentReq.studentDepartmentId()).orElseThrow(()->new ResourceNotFoundException("student with id" + studentReq.studentDepartmentId()+ "not found"));

          StudentProfile prof= studentProfileRepository.findById(studentReq.studentProfileId()).orElseThrow(()->new ResourceNotFoundException("student with id" + studentReq.studentProfileId()+ "not found"));

         Student s = modelMapper.map(studentReq,Student.class);
        s.setName(studentReq.name());
        s.setAge(studentReq.age());
        s.setSubject(studentReq.subject());
        s.setEmail(studentReq.email());
        s.setRollno(studentReq.rollno());
        s.setStudentDepartment(dept);
        s.setStudentProfile(prof);

        Student saved=studentRepository.save(s);
        return modelMapper.map(saved, StudentResponse.Create.class);
    }
    @Cacheable(value = "Student" , key="#id" )
    @Override
    public StudentResponse.Get getById(Long id) {
        return studentRepository.findById(id)
                .map(d -> modelMapper.map(d, StudentResponse.Get.class))
                .orElseThrow(()->new ResourceNotFoundException("student with id" + id+ "not found"));


    }
    @Cacheable(value = "student_list" )
    @Override

    public List<StudentResponse.Get> getAll(){
        return studentRepository.findAll().stream().
                map(d -> modelMapper.map(d, StudentResponse.Get.class))
                .toList();


    }
    @Caching(evict={
            @CacheEvict(value="student", key="#req.id()"),
            @CacheEvict(value="student", allEntries = true),

    })
    @Override
    public StudentResponse.Update updateById(StudentRequest.Update req) {
        Student s= studentRepository.findById(req.id())
                .orElseThrow(()->new ResourceNotFoundException("student with id" + req.id()+ "not found"));
        StudentDepartment dept= studentDepartmentRepository.findById(req.studentDepartmentId()).orElseThrow(()->new ResourceNotFoundException("student with id" + req.studentDepartmentId()+ "not found"));

        StudentProfile prof= studentProfileRepository.findById(req.studentProfileId()).orElseThrow(()->new ResourceNotFoundException("student with id" + req.studentProfileId()+ "not found"));
            s.setName(req.name());
            s.setAge(req.age());
            s.setSubject(req.subject());
            s.setEmail(req.email());
            s.setRollno(req.rollno());
            s.setStudentProfile(prof);
            s.setStudentDepartment(dept);


            Student saved = studentRepository.save(s);
            return modelMapper.map(saved, StudentResponse.Update.class);

        }


    @Override
    public boolean deleteById(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    public StudentResponse.DepartmentCount countByDepartment(Long departmentId) {
        if (!studentDepartmentRepository.existsById(departmentId)) {
            throw new ResourceNotFoundException("Department not found with id " + departmentId);
        }
        int total = studentRepository.countStudentsByDepartment(departmentId);
        return new StudentResponse.DepartmentCount(departmentId, total);
    }

}


