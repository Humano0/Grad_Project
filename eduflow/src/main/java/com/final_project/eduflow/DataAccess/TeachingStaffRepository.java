package com.final_project.eduflow.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.final_project.eduflow.Data.Entities.TeachingStaff;

import java.util.List;

public interface TeachingStaffRepository extends JpaRepository<TeachingStaff, Long>{
    TeachingStaff findByEmail(String email);
    TeachingStaff findByEmailAndPassword( String email, String password);
    TeachingStaff findById(long id);

    //TODO make this method work with DTO
    @Query(value="SELECT ts.id ,ts.email, ts.name, ts.surname, ts.department_id, ts.role, d.name From teaching_staff ts JOIN department d ON ts.department_id = d.id",nativeQuery = true)
    List<Object[]> findAllWithDepartments();

    boolean existsByDepartmentIdAndRole(int departmentId, String role);

    boolean existsByRole(String role);
}