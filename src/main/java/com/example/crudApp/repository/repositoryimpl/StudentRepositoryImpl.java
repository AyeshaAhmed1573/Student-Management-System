package com.example.crudApp.repository.repositoryimpl;

import com.example.crudApp.repository.repositorycustom.StudentRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

public class StudentRepositoryImpl  implements StudentRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean existsByStudentDepartmentId(Long id) {
        Long count = entityManager.createQuery("SELECT COUNT(s) FROM Student s WHERE s.studentDepartment.id =:id", Long.class)
                .setParameter("id", id)
                .getSingleResult();
        return count > 0;

    }
    @Override
    public boolean existsByStudentProfileId(Long id) {
        Long count = entityManager.createQuery("SELECT COUNT(s) FROM Student s WHERE s.studentProfile.id =:id", Long.class)
                .setParameter("id", id)
                .getSingleResult();
        return count > 0;

    }
    @Override
    public int countStudentsByDepartment(Long departmentId) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("count_students_by_department")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, departmentId);

        query.execute();
        return (Integer) query.getOutputParameterValue(2);
    }


}
