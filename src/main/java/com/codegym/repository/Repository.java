package com.codegym.repository;

import com.codegym.model.Blog;

import java.util.List;

public interface Repository<T> {
    List<T> findAll();
    T findById(Long id);
    void save(Blog blog);
    void remove(Long id);
}
