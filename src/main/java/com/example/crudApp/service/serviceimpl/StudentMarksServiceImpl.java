package com.example.crudApp.service.serviceimpl;

import ch.qos.logback.core.model.Model;
import com.example.crudApp.dto.request.StudentMarksRequest;
import com.example.crudApp.dto.response.StudentDepartmentResponse;
import com.example.crudApp.dto.response.StudentMarksResponse;
import com.example.crudApp.entity.Student;
import com.example.crudApp.entity.StudentDepartment;
import com.example.crudApp.entity.StudentMarks;
import com.example.crudApp.exception.ResourceNotFoundException;
import com.example.crudApp.repository.StudentMarksRepository;
import com.example.crudApp.repository.StudentRepository;
import com.example.crudApp.service.services.StudentMarksService;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class StudentMarksServiceImpl implements StudentMarksService {
    private StudentMarksRepository studentMarksRepository;
    private StudentRepository studentRepository;
    private ModelMapper modelMapper;
    public StudentMarksServiceImpl(ModelMapper modelMapper, StudentMarksRepository studentMarksRepository, StudentRepository studentRepository){
        this.studentMarksRepository=studentMarksRepository;
        this.studentRepository=studentRepository;
        this.modelMapper=modelMapper;

    }
    @Override
    public StudentMarksResponse.Create Create(StudentMarksRequest.Create req){
//        if (req.studentId()==null){
//            return null;
//
//        }
//        Optional<Student> result= studentRepository.findById(req.studentId());
//        if (result.isEmpty()){
//            return null;
//        }
        Student result= studentRepository.findById(req.studentId()).orElseThrow(()->new ResourceNotFoundException("student with id" + req.studentId()+ "not found"));

        StudentMarks d= modelMapper.map(req,StudentMarks.class);
        d.setScore(req.score());
        d.setExam_date(req.exam_date());
        d.setStudent(result);


        StudentMarks saved= studentMarksRepository.save(d);
        return modelMapper.map(saved, StudentMarksResponse.Create.class );

    }
    @Cacheable(value="mark_list")
    @Override

    public List<StudentMarksResponse.Get> getAll(){
        return studentMarksRepository.findAll().stream()
                .map(d->modelMapper.map(d,StudentMarksResponse.Get.class))
                .toList();
    }


    @Cacheable (value="mark", key="#id")
    @Override

    public StudentMarksResponse.Get getById(Long id){
        return studentMarksRepository.findById(id)
                .map(d->modelMapper.map(d,StudentMarksResponse.Get.class))
                .orElseThrow(()->new ResourceNotFoundException("student with id" + id+ "not found"));
    }

    @Caching(evict={
            @CacheEvict(value = "mark", allEntries = true),
            @CacheEvict(value = "mark_list",key="#req.id()" )
    })
    @Override
    public StudentMarksResponse.Update updateById(StudentMarksRequest.Update req){
//        Optional <StudentMarks> result=studentMarksRepository.findById(req.id());
        StudentMarks d= studentMarksRepository.findById(req.id()).orElseThrow(()->new ResourceNotFoundException("student with id" + req.id()+ "not found"));
        d.setScore(req.score());
        d.setExam_date(req.exam_date());
        StudentMarks save= studentMarksRepository.save(d);
        return modelMapper.map(save,StudentMarksResponse.Update.class);

    }
    @Override
    public StudentMarksResponse.Delete deleteById(Long id){
        Optional <StudentMarks> result= studentMarksRepository.findById(id);

        if(result.isPresent()){
            studentMarksRepository.deleteById(id);
            return new StudentMarksResponse.Delete("Marks is deleted");
        }
        return null;
    }
}
