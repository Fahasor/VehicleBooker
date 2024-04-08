package vehbook.vehiclebooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehbook.vehiclebooker.model.UserIdentity;
import vehbook.vehiclebooker.repository.UserIdentityRepository;

import java.math.BigInteger;
import java.util.List;

@Service
public class UserIdentityService {
    private final UserIdentityRepository users;

    @Autowired
    public UserIdentityService(UserIdentityRepository userIdentityRepository) {
        this.users = userIdentityRepository;
    }

    public UserIdentity getUserIdentity(String phoneNumber) {
        return users.findByPhoneNumber(phoneNumber);
    }
    public UserIdentity createUserIdentity(UserIdentity userIdentity) {
        return users.save(userIdentity);
    }
}
