package vehbook.vehiclebooker.service;

import org.springframework.stereotype.Service;
import vehbook.vehiclebooker.model.UserIdentity;
import vehbook.vehiclebooker.repository.UserIdentityRepository;

@Service
public class UserIdentityService {
    private final UserIdentityRepository users = new UserIdentityRepository();

    public UserIdentityService() {
        // shows explicitly that this constructor exists
    }

    public UserIdentity getUser(Long id) {
        return users.getUser(id); 
    }
}
