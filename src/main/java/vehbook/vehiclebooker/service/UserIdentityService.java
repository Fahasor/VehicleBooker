package vehbook.vehiclebooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehbook.vehiclebooker.model.UserIdentity;
import vehbook.vehiclebooker.repository.UserIdentityRepository;

import java.util.Optional;

@Service
public class UserIdentityService {
    private final UserIdentityRepository users;

    @Autowired
    public UserIdentityService(UserIdentityRepository userIdentityRepository) {
        this.users = userIdentityRepository;
    }

    public UserIdentity getUserIdentity(String phoneNumber) {
        return users.findByPhoneNumber(phoneNumber).orElseThrow();
    }
    public void createUserIdentity(UserIdentity userIdentity) {
        users.save(userIdentity);
    }
    public void update(UserIdentity userIdentity) {
        users.findById(userIdentity.getId()).orElseThrow();
        users.save(userIdentity);
    }
    public void deleteById(Long id) {
        users.delete(users.findById(id).orElseThrow());
    }
}
