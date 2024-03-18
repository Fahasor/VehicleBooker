package vehbook.vehiclebooker.service;

import org.springframework.stereotype.Service;
import vehbook.vehiclebooker.model.User;
import vehbook.vehiclebooker.repository.UserRepository;

@Service
public class UserService {
    private UserService() {}
    public static User getUser(Long id) {
        return UserRepository.getUser(id);
    }
}
