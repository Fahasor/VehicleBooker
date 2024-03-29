package vehbook.vehiclebooker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vehbook.vehiclebooker.model.UserIdentity;
import vehbook.vehiclebooker.service.UserIdentityService;

@RestController
public class UserIdentityController {
    private final UserIdentityService userService = new UserIdentityService();
    @GetMapping("/users")
    public UserIdentity getUser(@RequestParam Long id) {
        return userService.getUser(id);
    }
}
