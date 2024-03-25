package vehbook.vehiclebooker.repository;

import vehbook.vehiclebooker.model.User;

public class UserRepository {
    public UserRepository() {}

    public User getUser(Long id) {
        return new User(id, "+375296897070");
    }
}
