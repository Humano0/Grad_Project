package com.final_project.services;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.final_project.DataAccess.Interfaces.IStudentRepository;
import com.final_project.DataAccess.Interfaces.ITeachingStaffRepository;
import com.final_project.datalayer.Dto.LoginUser;
import com.final_project.services.Interfaces.IUserService;

@Service
public class UserService implements IUserService{
    
    private final ITeachingStaffRepository teachingStaffRepository;
    private final IStudentRepository studentRepository;
    
    @Autowired
    public UserService(ITeachingStaffRepository teachingStaffRepository, IStudentRepository studentRepository) {
        this.teachingStaffRepository = teachingStaffRepository;
        this.studentRepository = studentRepository;
    }

    public boolean isStudent(String email) {
        return studentRepository.getStudentByEmail(email) != null;
    }

    public boolean isStaff(String email) {
        return teachingStaffRepository.getTeachingStaffByEmail(email) != null;
    }

    public boolean isUser(String email) {
        return isStudent(email) || isStaff(email);
    }

    public boolean isUser(LoginUser user) {
        if (isStudent(user.getEmail() )) {
            
        }
        else if (isStaff(user.getEmail())) {
            
        }
        return false;
    }
}
