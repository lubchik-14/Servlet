package com.ithillel.javaee.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
public class User implements Serializable {
    private UserParameters userParameters;

    @Data
    @AllArgsConstructor
    public static class UserParameters {
        private final String login;
        private final String password;
        private final Role role;
    }

    public static User of(User user) {
        return new User(
                new UserParameters(
                        user.getUserParameters().getLogin(),
                        user.getUserParameters().getPassword(),
                        user.getUserParameters().getRole()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(userParameters.login, user.userParameters.login)
                && Objects.equals(userParameters.password, user.userParameters.password)
                && Objects.equals(userParameters.role, user.userParameters.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userParameters.login, userParameters.password, userParameters.role);
    }

    @Override
    public String toString() {
        return "User{" +
                "login=" + userParameters.getLogin() +
                "password=" + userParameters.getPassword() +
                "role=" + userParameters.getRole() +
                '}';
    }
}
