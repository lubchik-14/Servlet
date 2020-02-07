package com.ithillel.javaee.services;

import com.ithillel.javaee.data.Database;
import com.ithillel.javaee.model.User;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class UserService implements Service<User, User.UserParameters>{
    Database database;

    @Override
    public User get(int userId) {
        return database.getUser(userId);
    }

    @Override
    public int getId(User.UserParameters parameters) {
        return database.getUserId(parameters);
    }

    @Override
    public Map<Integer, User> getAll() {
        return database.getUsers();
    }

    @Override
    public int add(User user) {
        return database.putUser(user.getUserParameters());
    }

    @Override
    public User delete(int id) {
        return database.removeUser(id);
    }
}
