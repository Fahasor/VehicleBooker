package vehbook.vehiclebooker.service;

import org.springframework.stereotype.Service;
import vehbook.vehiclebooker.model.User;

@Service
public class UserService {
    public static User getUser(Long id) {
        return new User(id, "+3753356703174");
    }
}
