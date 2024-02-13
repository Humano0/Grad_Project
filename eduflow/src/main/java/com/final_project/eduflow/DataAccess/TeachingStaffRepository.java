package com.final_project.eduflow.DataAccess;

import org.aspectj.weaver.ast.Var;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.final_project.eduflow.Data.Entities.TeachingStaff;
import com.final_project.eduflow.Presentation.ResponseClasses.StaffInfoForAdmin;
import com.final_project.eduflow.Data.DTO.StaffWithDepartmentsDTO;

import java.util.List;

public interface TeachingStaffRepository extends JpaRepository<TeachingStaff, Long>{
    TeachingStaff findByEmail(String email);
    TeachingStaff findByEmailAndPassword( String email, String password);

    //TODO make this method work with DTO
    @Query(value="SELECT ts.id ,ts.email, ts.name, ts.surname, ts.department_id, ts.role, d.name From teaching_staff ts JOIN department d ON ts.department_id = d.id",nativeQuery = true)
    List<Object[]> findAllWithDepartments();
}