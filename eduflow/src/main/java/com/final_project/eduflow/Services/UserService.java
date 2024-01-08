package com.final_project.eduflow.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.final_project.eduflow.Data.Student;
import com.final_project.eduflow.Data.TeachingStaff;
import com.final_project.eduflow.Data.Dto.LoginUser;
import com.final_project.eduflow.Data.Dto.User;
import com.final_project.eduflow.DataAccess.StudentRepository;
import com.final_project.eduflow.DataAccess.TeachingStaffRepository;
import com.final_project.eduflow.Services.Interfaces.IUserService;

@Service
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
            Student temp = studentRepositoy.findByEmailAndPassword(loginUser.getEmail(), loginUser.getPassword());
            return new User(temp.getFullName(), temp.getEmail(), temp.getPassword(), "student", temp.getId());
        }
        else if(isStaff(loginUser.getEmail(), loginUser.getPassword())){
            TeachingStaff temp=teachingRepository.findByEmailAndPassword(  loginUser.getEmail(), loginUser.getPassword());
            return new User(temp.getFullName(), temp.getEmail(), temp.getPassword(), temp.getRole(), temp.getId());
        }
        return null;
    }

}
