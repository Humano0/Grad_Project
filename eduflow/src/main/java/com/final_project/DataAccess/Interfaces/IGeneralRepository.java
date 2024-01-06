package com.final_project.DataAccess.Interfaces;

import java.util.List;

public interface IGeneralRepository<T> {
    T getById(int id);
    List<T> getAll();
    void add(T entity);
    void update(T entity);
    void delete(T entity);
}