package com.final_project.eduflow.Services.Interfaces;

import com.final_project.eduflow.Data.DTO.UserLoginEntity;
import com.final_project.eduflow.Data.DTO.User;

public interface IUserService {
    
    boolean isStudent(String email, String password);
    boolean isStaff(String email, String password);

    boolean isUser(String email, String password);

    User findUser(UserLoginEntity userLoginEntity);
}
