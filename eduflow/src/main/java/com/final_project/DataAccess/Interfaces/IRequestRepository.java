package com.final_project.DataAccess.Interfaces;

import com.final_project.datalayer.Request;

public interface IRequestRepository extends org.springframework.data.jpa.repository.JpaRepository<Request, Long> {
    void addRequest(Request request);
}