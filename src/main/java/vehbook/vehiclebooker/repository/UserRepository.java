package vehbook.vehiclebooker.repository;

import vehbook.vehiclebooker.model.User;

public class UserRepository {
    private UserRepository() {}
    public static User getUser(Long id) {
        return new User(id, "+375296897070");
    }
}
