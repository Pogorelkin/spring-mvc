package com.springmvc.repositories;

import java.util.List;

public interface BasicDAO<T> {
    List<T> findAll();

    T findById(long id);

    long add(T entity);

    long update(long id, T entity);

    long deleteById(long id);
}
