package vehbook.vehiclebooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehbook.vehiclebooker.model.User;
import vehbook.vehiclebooker.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository users;

    @Autowired
    public UserService(UserRepository users) {
        this.users = users;
    }

    public User getUser(Long id) {
        return users.getUser(id); 
    }
}
