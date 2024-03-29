package vehbook.vehiclebooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehbook.vehiclebooker.model.UserIdentity;
import vehbook.vehiclebooker.repository.UserIdentityRepository;

import java.math.BigInteger;

@Service
public class UserIdentityService {
    private final UserIdentityRepository users;

    @Autowired
    public UserIdentityService(UserIdentityRepository userIdentityRepository) {
        this.users = userIdentityRepository;
    }

    public UserIdentity getUserIdentity(BigInteger id) {
        return users.findById(id).orElseThrow();
    }
    public UserIdentity createUserIdentity(UserIdentity userIdentity) {
        return users.save(userIdentity);
    }
}
