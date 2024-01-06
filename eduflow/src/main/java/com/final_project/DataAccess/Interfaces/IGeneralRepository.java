package com.final_project.DataAccess.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IGeneralRepository<T> extends JpaRepository<T, Long> { }