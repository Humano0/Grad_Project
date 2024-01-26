package com.final_project.eduflow.DataAccess;


import org.springframework.data.jpa.repository.JpaRepository;

import com.final_project.eduflow.Data.Entities.RequestType;

import java.util.List;

public interface RequestTypeRepository extends JpaRepository<RequestType, Long>{

}
