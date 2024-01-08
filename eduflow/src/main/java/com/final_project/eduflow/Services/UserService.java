package com.final_project.eduflow.Services;

import org.springframework.beans.factory.annotation.Autowired;

import com.final_project.eduflow.Data.DTO.UserLoginEntity;
import com.final_project.eduflow.Data.DTO.User;
import com.final_project.eduflow.DataAccess.StudentRepository;
import com.final_project.eduflow.DataAccess.TeachingStaffRepository;
import com.final_project.eduflow.Services.Interfaces.IUserService;

public class UserService implements IUserService{
    
    private final StudentRepository studentRepository;
    private final TeachingStaffRepository teachingRepository;

    @Autowired
    public UserService(StudentRepository studentRepository, TeachingStaffRepository teachingRepository) {
        this.studentRepository = studentRepository;
        this.teachingRepository = teachingRepository;
    }

    @Override
    public boolean isStudent(String email, String password) {
        return studentRepository.findByEmailAndPassword(email, password) != null;
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
    public User findUser(UserLoginEntity userLoginEntity) {
        if(isStudent(userLoginEntity.getEmail(), userLoginEntity.getPassword())){
            return studentRepository.findByEmailAndPassword(userLoginEntity.getEmail(), userLoginEntity.getPassword());
        }
        else if(isStaff(userLoginEntity.getEmail(), userLoginEntity.getPassword())){
            return teachingRepository.findByEmailAndPassword(  userLoginEntity.getEmail(), userLoginEntity.getPassword());
        }
        return null;
    }

}
