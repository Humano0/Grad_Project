package com.final_project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.final_project.DataAccess.Interfaces.IStudentRepository;
import com.final_project.DataAccess.Interfaces.ITeachingStaffRepository;
import com.final_project.DataAccess.Repositories.StudentRepository;
import com.final_project.DataAccess.Repositories.TeachingStaffRepository;
import com.final_project.datalayer.Dto.LoginUser;
import com.final_project.datalayer.Dto.User;
import com.final_project.services.Interfaces.IUserService;

@Service
/* @ComponentScan({ "com.final_project.DataAccess"}) */
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

    public User findUser(LoginUser user) {
        if (isStudent(user.getEmail() )) {
            var student = studentRepository.getStudentByEmail(user.getEmail());
            return new User(student.getFullName(), student.getEmail(), student.getPassword(), "student", student.getId());
        }
        else if (isStaff(user.getEmail())) {
            var staff = teachingStaffRepository.getTeachingStaffByEmail(user.getEmail());
            return new User(staff.getFullName(), staff.getEmail(), staff.getPassword(), "staff", staff.getId());
        }
        return null;
    }
}
