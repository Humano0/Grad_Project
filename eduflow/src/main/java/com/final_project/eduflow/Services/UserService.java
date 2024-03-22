package com.final_project.eduflow.Services;

import com.final_project.eduflow.Data.DTO.StudentSideBarInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.final_project.eduflow.Data.DTO.UserLoginEntity;
import com.final_project.eduflow.Data.Entities.Student;
import com.final_project.eduflow.Data.Entities.TeachingStaff;
import com.final_project.eduflow.Data.View.UsersView;
import com.final_project.eduflow.Data.DTO.User;
import com.final_project.eduflow.DataAccess.StudentRepository;
import com.final_project.eduflow.DataAccess.TeachingStaffRepository;
import com.final_project.eduflow.DataAccess.UsersViewRepository;
import com.final_project.eduflow.Services.Interfaces.IUserService;

@Service
public class UserService implements IUserService{
    
    private final StudentRepository studentRepository;
    private final TeachingStaffRepository teachingRepository;
    private final UsersViewRepository usersViewRepository;

    @Autowired
    public UserService(StudentRepository studentRepository, TeachingStaffRepository teachingRepository, UsersViewRepository usersViewRepository) {
        this.studentRepository = studentRepository;
        this.teachingRepository = teachingRepository;
        this.usersViewRepository = usersViewRepository;
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
    public User findUser(UserLoginEntity loginUser) {
/*         if(isStudent(loginUser.getEmail(), loginUser.getPassword())){
            Student temp = studentRepository.findByEmailAndPassword(loginUser.getEmail(), loginUser.getPassword());
            return new User(temp.getFullName(), temp.getEmail(), temp.getPassword(), "Student", temp.getId());
        }
        else if(isStaff(loginUser.getEmail(), loginUser.getPassword())){
            TeachingStaff temp=teachingRepository.findByEmailAndPassword(  loginUser.getEmail(), loginUser.getPassword());
            return new User(temp.getFullName(), temp.getEmail(), temp.getPassword(), temp.getRole(), temp.getId());
        }
        return null; */

        try{
            UsersView tempUser = usersViewRepository.findByEmailAndPassword(loginUser.getEmail(), loginUser.getPassword());
            return new User(tempUser.getFullName(), tempUser.getEmail(), tempUser.getPassword(), tempUser.getRole(), tempUser.getId());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return e.getMessage().equals("No value present") ? null : null;
        }
        
    }

    @Override
    public StudentSideBarInfoEntity getStudentSideBarInfo(long studentId) {
        return studentRepository.findById(studentId)
                .map(s -> new StudentSideBarInfoEntity(s.getFirstname(), s.getLastname(), s.getId()))
                .orElse(null);
    }

    @Override
    public Student getStudent(long studentId) {
        if( studentRepository.findById(studentId).isPresent()){
            return studentRepository.findById(studentId).get();
        }else {
            return null;
        }
    }

    @Override
    public TeachingStaff getTeachingStaff(long staffId) {
        return teachingRepository.findById(staffId);
    }

}
