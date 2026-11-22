package com.example.crudApp.service.serviceimpl;

import com.example.crudApp.dto.request.StudentDepartmentRequest;
import com.example.crudApp.dto.response.StudentDepartmentResponse;
import com.example.crudApp.entity.Student;
import com.example.crudApp.entity.StudentDepartment;
import com.example.crudApp.exception.ResourceNotFoundException;
import com.example.crudApp.repository.StudentDepartmentRepository;
import com.example.crudApp.repository.StudentRepository;
import com.example.crudApp.service.services.StudentDepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class StudentDepartmentServiceImpl implements StudentDepartmentService {
    private StudentDepartmentRepository studentDepartmentRepository;
    private StudentRepository studentRepository;
    private ModelMapper modelMapper;
    public StudentDepartmentServiceImpl(ModelMapper modelMapper,StudentDepartmentRepository studentDepartmentRepository, StudentRepository studentRepository){
        this.studentDepartmentRepository=studentDepartmentRepository;
        this.studentRepository=studentRepository;
        this.modelMapper=modelMapper;
    }
    @Override
    public StudentDepartmentResponse.Create Create(StudentDepartmentRequest.Create req){
        StudentDepartment d= modelMapper.map(req,StudentDepartment.class);

        d.setName(req.name());
        d.setCreatedAt(LocalDateTime.now());
        StudentDepartment saved= studentDepartmentRepository.save(d);
        return modelMapper.map(saved,StudentDepartmentResponse.Create.class);

    }
    @Cacheable(value="department_list")
    @Override
    public List<StudentDepartmentResponse.Get> getAll() {
        return studentDepartmentRepository.findAll().stream()
                .map(d -> modelMapper.map(d, StudentDepartmentResponse.Get.class))
                .toList();
    }


    @Cacheable(value = "department" , key="#id")
    @Override
    public StudentDepartmentResponse.Get getById(Long id){
         return studentDepartmentRepository.findById(id)
                 .map(d->modelMapper.map(d,StudentDepartmentResponse.Get.class))
                 .orElseThrow(()->new ResourceNotFoundException("student with id" + id + "not found"));


    }
    @Override
    @Caching(evict={
            @CacheEvict(value="department" , key="#req.id()"),
            @CacheEvict(value = "department_list", allEntries = true)
    })
    public StudentDepartmentResponse.Update updateById(StudentDepartmentRequest.Update req) {
        StudentDepartment d = studentDepartmentRepository.findById(req.id())
                .orElseThrow(()->new ResourceNotFoundException("student with id" + req.id()+ "not found"));
        d.setName(req.name());


        return modelMapper.map(studentDepartmentRepository.save(d), StudentDepartmentResponse.Update.class);


    }
    @Override
    public StudentDepartmentResponse.Delete deleteById(Long id){
       Optional <StudentDepartment> result= studentDepartmentRepository.findById(id);
               if (!studentDepartmentRepository.existsById(id)){
                   throw new ResourceNotFoundException("Department not found with id " + id);
               }
        if (studentRepository.existsByStudentDepartmentId(id)){
            return new StudentDepartmentResponse.Delete("Department still has students");
        }

        if(result.isPresent()){
             studentDepartmentRepository.deleteById(id);
             return new StudentDepartmentResponse.Delete("Department is deleted");
        }
        return null;
    }
}
