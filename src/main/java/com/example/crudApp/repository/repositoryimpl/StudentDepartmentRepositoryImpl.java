package com.example.crudApp.repository.repositoryimpl;

import com.example.crudApp.dto.response.DepartmentCount;
import com.example.crudApp.repository.repositorycustom.StudentDepartmentRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class StudentDepartmentRepositoryImpl implements StudentDepartmentRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DepartmentCount> countStudentsPerDepartment() {
        return entityManager.createQuery("""
        select new com.example.crudApp.dto.response.DepartmentCount(d.name, count(s))
        from StudentDepartment d left join Student s on s.studentDepartment = d
        group by d.id, d.name
        """, DepartmentCount.class)
                .getResultList();
    }
}