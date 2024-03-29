package vehbook.vehiclebooker.repository;

import vehbook.vehiclebooker.model.UserIdentity;

public class UserIdentityRepository {
    public UserIdentityRepository() {
        // shows explicitly that this constructor exists
    }

    public UserIdentity getUser(Long id) {
        return new UserIdentity();
    }
}
