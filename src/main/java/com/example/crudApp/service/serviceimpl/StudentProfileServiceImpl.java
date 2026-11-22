package com.example.crudApp.service.serviceimpl;

import com.example.crudApp.dto.request.StudentProfileRequest;
import com.example.crudApp.dto.response.StudentProfileResponse;
import com.example.crudApp.entity.StudentProfile;
import com.example.crudApp.exception.ResourceNotFoundException;
import com.example.crudApp.repository.StudentProfileRepository;
import com.example.crudApp.repository.StudentRepository;
import com.example.crudApp.service.services.StudentProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class StudentProfileServiceImpl implements StudentProfileService {
    private StudentProfileRepository studentProfileRepository;
    private StudentRepository studentRepository;
    private ModelMapper modelMapper;
    public StudentProfileServiceImpl(ModelMapper modelMapper,StudentProfileRepository studentProfileRepository, StudentRepository studentRepository){
        this.studentProfileRepository=studentProfileRepository;
        this.studentRepository=studentRepository;
        this.modelMapper=modelMapper;
    }
    @Override
    public StudentProfileResponse.Create Create(StudentProfileRequest.Create req){
        StudentProfile d= modelMapper.map(req,StudentProfile.class);
        d.setPhone(req.phone());
        d.setAddress(req.address());
        d.setGuardianname(req.guardianname());
        StudentProfile saved= studentProfileRepository.save(d);
        return modelMapper.map(saved, StudentProfileResponse.Create.class);


    }
    @Override
    public List<StudentProfileResponse.Get> getAll(){
        return studentProfileRepository.findAll().stream()
                .map(d->modelMapper.map(d,StudentProfileResponse.Get.class))
                .toList();

    }
    @Override
    public StudentProfileResponse.Get getById(Long id){
       return studentProfileRepository.findById(id)
               .map(d->modelMapper.map(d,StudentProfileResponse.Get.class))
               .orElseThrow(()->new ResourceNotFoundException("student with id" + id+ "not found"));
    }
    @Override
    public StudentProfileResponse.Update updateById(StudentProfileRequest.Update req){
        StudentProfile d= studentProfileRepository.findById(req.id())
        .orElseThrow(()->new ResourceNotFoundException("student with id" + req.id()+ "not found"));
            d.setPhone(req.phone());
            d.setAddress(req.address());
            d.setGuardianname(req.guardianname());
            StudentProfile save= studentProfileRepository.save(d);
            return modelMapper.map(save,StudentProfileResponse.Update.class);
        }


    @Override
    public StudentProfileResponse.Delete deleteById(Long id){
        Optional <StudentProfile> result= studentProfileRepository.findById(id);

        if (studentRepository.existsByStudentProfileId(id)){
            return new StudentProfileResponse.Delete("Profile still has students");
        }

        if(result.isPresent()){
            studentProfileRepository.deleteById(id);
            return new StudentProfileResponse.Delete("Profile is deleted");
        }
        return null;
    }
}
