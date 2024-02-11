package com.final_project.eduflow.Services;

import com.final_project.eduflow.Presentation.ResponseClasses.ListDepartments;
import com.final_project.eduflow.Services.Interfaces.IDepartmentService;
import org.springframework.stereotype.Service;
import com.final_project.eduflow.DataAccess.DepartmentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService implements IDepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    @Override
    public List<ListDepartments> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(department -> new ListDepartments(department.getId(), department.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ListDepartments> getAllDepartmentsByFacultyId(Long facultyId) {
        return departmentRepository.findByFacultyId(facultyId).stream()
                .map(department -> new ListDepartments(department.getId(), department.getName()))
                .collect(Collectors.toList());
    }
}
