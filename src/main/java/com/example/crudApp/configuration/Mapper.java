package com.example.crudApp.configuration;
import com.example.crudApp.dto.response.StudentMarksResponse;
import com.example.crudApp.dto.response.StudentResponse;
import com.example.crudApp.entity.Student;
import com.example.crudApp.entity.StudentMarks;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.record.RecordModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mapper {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mm = new ModelMapper();
        mm.registerModule(new RecordModule());
        mm.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        mm.addConverter((Converter<Student, StudentResponse.Create>) ctx -> {
            Student s = ctx.getSource();
            return new StudentResponse.Create(s.getId(), s.getName(), s.getEmail(), s.getAge(),
                    s.getRollno(), s.getSubject(), profileId(s), deptId(s));
        }, Student.class, StudentResponse.Create.class);

        mm.addConverter((Converter<StudentMarks, StudentMarksResponse.Create>) ctx -> {
            StudentMarks m = ctx.getSource();
            Student s = m.getStudent();
            return new StudentMarksResponse.Create(m.getId(), m.getScore(), m.getExam_date(),
                    s == null ? null : s.getId(),
                    s == null ? null : s.getName());
        }, StudentMarks.class, StudentMarksResponse.Create.class);

        mm.addConverter((Converter<Student, StudentResponse.Get>) ctx -> {
            Student s = ctx.getSource();
            return new StudentResponse.Get(s.getId(), s.getName(), s.getEmail(), s.getAge(),
                    s.getRollno(), s.getSubject(), profileId(s), deptId(s));
        }, Student.class, StudentResponse.Get.class);
        mm.addConverter((Converter<StudentMarks, StudentMarksResponse.Get>) ctx -> {
            StudentMarks m = ctx.getSource();
            Student s = m.getStudent();
            return new StudentMarksResponse.Get(m.getId(), m.getScore(), m.getExam_date(),
                    s == null ? null : s.getId(),
                    s == null ? null : s.getName());
        }, StudentMarks.class, StudentMarksResponse.Get.class);


        mm.addConverter((Converter<Student, StudentResponse.Update>) ctx -> {
            Student s = ctx.getSource();
            return new StudentResponse.Update(s.getId(), s.getName(), s.getEmail(), s.getAge(),
                    s.getRollno(), s.getSubject(), profileId(s), deptId(s));
        }, Student.class, StudentResponse.Update.class);
        mm.addConverter((Converter<StudentMarks, StudentMarksResponse.Update>) ctx -> {
            StudentMarks m = ctx.getSource();
            Student s = m.getStudent();
            return new StudentMarksResponse.Update(m.getId(), m.getScore(), m.getExam_date(),
                    s == null ? null : s.getId(),
                    s == null ? null : s.getName());
        }, StudentMarks.class, StudentMarksResponse.Update.class);

        return mm;
    }


    private static Long profileId(Student s) {
        return s.getStudentProfile() == null ? null : s.getStudentProfile().getId();
    }

    private static Long deptId(Student s) {
        return s.getStudentDepartment() == null ? null : s.getStudentDepartment().getId();
    }
}