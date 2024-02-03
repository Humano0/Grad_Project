package com.final_project.eduflow.DataAccess;

import com.final_project.eduflow.Data.View.UsersView;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersViewRepository extends JpaRepository<UsersView, String>{

    UsersView findByEmail(String email);
    UsersView findByEmailAndPassword(String email, String password);
    
} 
