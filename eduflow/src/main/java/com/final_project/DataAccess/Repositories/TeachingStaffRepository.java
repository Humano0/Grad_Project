package com.final_project.DataAccess.Repositories;

import com.final_project.DataAccess.Interfaces.ITeachingStaffRepository;
import com.final_project.datalayer.TeachingStaff;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TeachingStaffRepository implements ITeachingStaffRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addTeachingStaff(TeachingStaff teachingStaff) {
        entityManager.persist(teachingStaff);
    }

    @Override
    public TeachingStaff getTeachingStaffByEmail(String email) {
        return entityManager.find(TeachingStaff.class, email);
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
        return null;
    }
}
