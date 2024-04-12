package vehbook.vehiclebooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehbook.vehiclebooker.model.UserIdentity;
import vehbook.vehiclebooker.repository.UserIdentityRepository;

@Service
public class UserIdentityService {
    private final UserIdentityRepository userIdentityService;

    @Autowired
    public UserIdentityService(UserIdentityRepository userIdentityRepository) {
        this.userIdentityService = userIdentityRepository;
    }

    public UserIdentity findByPhoneNumber(String phoneNumber) {
        return userIdentityService.findByPhoneNumber(phoneNumber).orElseThrow();
    }
    public void create(UserIdentity userIdentity) {
        userIdentityService.save(userIdentity);
    }
    public void update(UserIdentity userIdentity) {
        userIdentityService.findById(userIdentity.getId()).orElseThrow();
        userIdentityService.save(userIdentity);
    }
    public void deleteById(Long id) {
        userIdentityService.delete(userIdentityService.findById(id).orElseThrow());
    }
}
