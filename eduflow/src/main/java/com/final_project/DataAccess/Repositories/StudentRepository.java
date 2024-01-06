package com.final_project.DataAccess.Repositories;

import com.final_project.DataAccess.Interfaces.IStudentRepository;
import com.final_project.datalayer.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StudentRepository implements IStudentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Student getByEmail(String email) {
        return null;
    }

    @Override
    public List<Student> getStudentsByDepartmentId(Long departmentId) {
        return null;
    }

    @Override
    public List<Student> getStudentsByAdviserId(Long adviserId) {
        return null;
    }
}
