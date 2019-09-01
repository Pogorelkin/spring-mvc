package com.springmvc.repositories;

import java.util.List;

public interface BasicRepository<T> {
    void add(T object);

    List<T> getAll();

    T getById(long id);

    void update(T object);

    void delete(T object);
}
