package com.final_project.services.Interfaces;

import com.final_project.datalayer.Dto.LoginUser;
import com.final_project.datalayer.Dto.User;

public interface IUserService {

    public boolean isStudent(String email);

    public boolean isStaff(String email);

    public boolean isUser(String email);

    public User findUser(LoginUser user);
    
    
}