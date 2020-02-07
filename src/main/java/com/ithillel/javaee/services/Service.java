package com.ithillel.javaee.services;

import com.ithillel.javaee.model.User;

import java.util.Map;

public interface Service <T, U> {
    T get(int id);
    int getId(U parameters);
    Map<Integer, T> getAll();
    int add(T value);
    T delete(int id);
}
