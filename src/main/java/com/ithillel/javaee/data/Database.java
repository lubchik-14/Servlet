package com.ithillel.javaee.data;

import com.ithillel.javaee.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Database {
    private AtomicInteger id = new AtomicInteger(0);
    private final Map<Integer, User> users = new HashMap<>();

    public int putUser(User.UserParameters userParameters) {
        if (getUserId(userParameters) != -1) {
            return -1;
        }
        User user = new User(userParameters);
        users.put(id.getAndIncrement(), user);
        return id.intValue();
    }

    public Map<Integer, User> getUsers() {
        return new HashMap<>(users);
    }

    public int getUserId(User.UserParameters userParameters) {
        return users.entrySet().stream()
                .filter(entry -> entry.getValue().equals(new User(userParameters)))
                .mapToInt(Map.Entry::getKey)
                .findFirst()
                .orElse(-1);
    }

    public User getUser (int userId) {
        return User.of(users.get(userId));
    }

    public User removeUser(int userId) {
        return users.remove(userId);
    }
}
