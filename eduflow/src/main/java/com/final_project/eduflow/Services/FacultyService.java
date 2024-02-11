package com.final_project.eduflow.Services;

import com.final_project.eduflow.DataAccess.FacultyRepository;
import com.final_project.eduflow.Presentation.ResponseClasses.ListFaculties;
import com.final_project.eduflow.Services.Interfaces.IFacultyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService implements IFacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
    @Override
    public List<ListFaculties> getAllFaculties() {
        return facultyRepository.findAll().stream()
                .map(faculty -> new ListFaculties(faculty.getId(), faculty.getName()))
                .collect(Collectors.toList());
    }
}
