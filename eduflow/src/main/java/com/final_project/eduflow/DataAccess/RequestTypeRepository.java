package com.final_project.eduflow.DataAccess;


import org.springframework.data.jpa.repository.JpaRepository;

import com.final_project.eduflow.Data.Entities.RequestType;

import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public interface RequestTypeRepository extends JpaRepository<RequestType, Long>{

}
