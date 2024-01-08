package com.final_project.DataAccess.Repositories;

import com.final_project.DataAccess.Interfaces.IStudentRepository;
import com.final_project.datalayer.Student;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StudentRepository implements IStudentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addStudent(Student student) {
        entityManager.persist(student);
    }

    @Override
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        Student student = entityManager.find(Student.class, id);
        if(student != null)
            entityManager.remove(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    @Transactional
    public Student getStudentByEmail(String email) {
        return entityManager.createQuery("SELECT s FROM Student s WHERE s.email = :email", Student.class)
                .setParameter("email", email)
                .getSingleResult();
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