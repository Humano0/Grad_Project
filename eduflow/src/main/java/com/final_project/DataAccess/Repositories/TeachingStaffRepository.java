package com.final_project.DataAccess.Repositories;

import com.final_project.DataAccess.Interfaces.ITeachingStaffRepository;
import com.final_project.datalayer.TeachingStaff;

import jakarta.transaction.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeachingStaffRepository implements ITeachingStaffRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(TeachingStaff teachingStaff) {
        entityManager.persist(teachingStaff);
    }

    @Override
    public void addTeachingStaff(TeachingStaff teachingStaff) {
        entityManager.persist(teachingStaff);
    }

    @Override
    public void updateTeachingStaff(TeachingStaff teachingStaff) {
        entityManager.merge(teachingStaff);
    }

    @Override
    public void deleteTeachingStaffById(Long id) {
        TeachingStaff teachingStaff = entityManager.find(TeachingStaff.class, id);
        if (teachingStaff != null)
            entityManager.remove(teachingStaff);
    }

    @Override
    @Transactional
    public TeachingStaff getTeachingStaffByEmail(String email) {
        return entityManager.createQuery("SELECT t FROM teaching_staff t WHERE t.email = :email", TeachingStaff.class)
                .setParameter("email", email)
                .getSingleResult();
        //return entityManager.find(TeachingStaff.class, email);
    }

    @Override
    public TeachingStaff getTeachingStaffById(Long id) {
        return entityManager.find(TeachingStaff.class, id);
    }

    @Override
    public List<TeachingStaff> getTeachingStaffByDepartmentId(Long departmentId) {
        return entityManager.createQuery("SELECT t FROM TeachingStaff t WHERE t.department.id = :departmentId", TeachingStaff.class)
                .setParameter("departmentId", departmentId)
                .getResultList();
    }

    @Override
    public TeachingStaff getTeachingStaffByStudentId(Long studentId) {
        return entityManager.createQuery("SELECT t FROM TeachingStaff t WHERE t.id = (SELECT s.adviserId FROM Student s WHERE s.id = :studentId)", TeachingStaff.class)
                .setParameter("studentId", studentId)
                .getSingleResult();
    }
}


/* import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachingStaffRepository extends JpaRepository<TeachingStaff, Integer> {
    TeachingStaff findByEmail(String email);
} */