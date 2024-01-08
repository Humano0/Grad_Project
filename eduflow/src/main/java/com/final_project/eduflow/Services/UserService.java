package com.final_project.eduflow.Services;

import org.springframework.beans.factory.annotation.Autowired;

import com.final_project.eduflow.Data.Dto.LoginUser;
import com.final_project.eduflow.Data.Dto.User;
import com.final_project.eduflow.DataAccess.StudentRepository;
import com.final_project.eduflow.DataAccess.TeachingStaffRepository;
import com.final_project.eduflow.Services.Interfaces.IUserService;

public class UserService implements IUserService{
    
    private final StudentRepository studentRepositoy;
    private final TeachingStaffRepository teachingRepository;

    @Autowired
    public UserService(StudentRepository studentRepositoy, TeachingStaffRepository teachingRepository) {
        this.studentRepositoy = studentRepositoy;
        this.teachingRepository = teachingRepository;
    }

    @Override
    public boolean isStudent(String email, String password) {
        return studentRepositoy.findByEmailAndPassword(email, password) != null;
    }

    @Override
    public boolean isStaff(String email, String password) {
        return teachingRepository.findByEmailAndPassword(email, password) != null;
    }

    @Override
    public boolean isUser(String email, String password) {
        return isStudent(email, password) || isStaff(email, password);
    }

    @Override
    public User findUser(LoginUser loginUser) {
        if(isStudent(loginUser.getEmail(), loginUser.getPassword())){
            return studentRepositoy.findByEmailAndPassword(loginUser.getEmail(), loginUser.getPassword());
        }
        else if(isStaff(loginUser.getEmail(), loginUser.getPassword())){
            return teachingRepository.findByEmailAndPassword(  loginUser.getEmail(), loginUser.getPassword());
        }
        return null;
    }

}
