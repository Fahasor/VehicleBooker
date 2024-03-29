package vehbook.vehiclebooker.repository;

import org.springframework.stereotype.Repository;
import vehbook.vehiclebooker.model.User;

@Repository
public class UserRepository {

    public UserRepository() {
        // shows explicitly that this constructor exists
    }

    public User getUser(Long id) {
        return new User(id, "+375296897070");
    }
}
