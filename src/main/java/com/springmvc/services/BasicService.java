package com.springmvc.services;

import java.util.List;

public interface BasicService<T> {
    void add(T object);

    List<T> getAll();

    T getById(long id);

    void update(T object);

    void delete(T object);
}
