package vehbook.vehiclebooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehbook.vehiclebooker.model.User;
import vehbook.vehiclebooker.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository users;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.users = userRepository;
    }

    public void create(User user) {
        users.save(user);
    }
    public User findByPhoneNumber(String phoneNumber) {
        return users.findByPhoneNumber(phoneNumber).orElseThrow();
    }
    public List<User> getAll() {
        return users.findAll();
    }
    public void update(User user) {
        users.findById(user.getId()).orElseThrow();
        users.save(user);
    }
    public void deleteById(Long id) {
        users.delete(users.findById(id).orElseThrow());
    }
}
