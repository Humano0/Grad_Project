package com.final_project.eduflow.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import com.final_project.eduflow.Data.Student;
import com.final_project.eduflow.Data.TeachingStaff;
import com.final_project.eduflow.Data.Dto.LoginUser;
import com.final_project.eduflow.Data.Dto.User;
=======
import com.final_project.eduflow.Data.DTO.UserLoginEntity;
import com.final_project.eduflow.Data.DTO.User;
>>>>>>> c921c1171c35baca58848819a16f7ebfb35a2413
import com.final_project.eduflow.DataAccess.StudentRepository;
import com.final_project.eduflow.DataAccess.TeachingStaffRepository;
import com.final_project.eduflow.Services.Interfaces.IUserService;

@Service
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
<<<<<<< HEAD
    public User findUser(LoginUser loginUser) {
        if(isStudent(loginUser.getEmail(), loginUser.getPassword())){
            Student temp = studentRepositoy.findByEmailAndPassword(loginUser.getEmail(), loginUser.getPassword());
            return new User(temp.getFullName(), temp.getEmail(), temp.getPassword(), "student", temp.getId());
        }
        else if(isStaff(loginUser.getEmail(), loginUser.getPassword())){
            TeachingStaff temp=teachingRepository.findByEmailAndPassword(  loginUser.getEmail(), loginUser.getPassword());
            return new User(temp.getFullName(), temp.getEmail(), temp.getPassword(), temp.getRole(), temp.getId());
=======
    public User findUser(UserLoginEntity userLoginEntity) {
        if(isStudent(userLoginEntity.getEmail(), userLoginEntity.getPassword())){
            return studentRepository.findByEmailAndPassword(userLoginEntity.getEmail(), userLoginEntity.getPassword());
        }
        else if(isStaff(userLoginEntity.getEmail(), userLoginEntity.getPassword())){
            return teachingRepository.findByEmailAndPassword(  userLoginEntity.getEmail(), userLoginEntity.getPassword());
>>>>>>> c921c1171c35baca58848819a16f7ebfb35a2413
        }
        return null;
    }

}
