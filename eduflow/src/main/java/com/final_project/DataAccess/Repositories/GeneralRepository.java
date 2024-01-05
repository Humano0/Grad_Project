package com.final_project.DataAccess.Repositories;

import java.util.List;
import java.util.ArrayList;

import com.final_project.DataAccess.Interfaces.IGeneralRepository;

public class GeneralRepository<T> implements IGeneralRepository<T> {
    private List<T> entities;

    public GeneralRepository() {
        entities = new ArrayList<>();
    }

    @Override
    public T getById(int id) {
        // implementation goes here
        return null;
    }

    @Override
    public List<T> getAll() {
        // implementation goes here
        return null;
    }

    @Override
    public void add(T entity) {
        // implementation goes here
    }

    @Override
    public void update(T entity) {
        // implementation goes here
    }

    @Override
    public void delete(T entity) {
        // implementation goes here
    }
}
