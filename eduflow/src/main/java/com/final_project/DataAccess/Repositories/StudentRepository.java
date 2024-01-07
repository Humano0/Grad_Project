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
        return entityManager.find(Student.class, email);
    }

    @Override
    public List<Student> getStudentsByDepartmentId(Long departmentId) {
        return entityManager.createQuery("SELECT s FROM Student s WHERE s.departmentId = :departmentId", Student.class)
                .setParameter("departmentId", departmentId)
                .getResultList();
    }

    @Override
    public List<Student> getStudentsByAdviserId(Long adviserId) {
        return entityManager.createQuery("SELECT s FROM Student s WHERE s.adviserId = :adviserId", Student.class)
                .setParameter("adviserId", adviserId)
                .getResultList();
    }
}