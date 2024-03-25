package vehbook.vehiclebooker.repository;

import vehbook.vehiclebooker.model.User;

public class UserRepository {
    public UserRepository() {
        // shows explicitly that this constructor exists
    }

    public User getUser(Long id) {
        return new User(id, "+375296897070");
    }
}
