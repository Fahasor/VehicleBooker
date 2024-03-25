package vehbook.vehiclebooker.service;

import org.springframework.stereotype.Service;
import vehbook.vehiclebooker.model.User;
import vehbook.vehiclebooker.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository users = new UserRepository();

    public UserService() {}

    public User getUser(Long id) {
        return users.getUser(id);
    }
}
