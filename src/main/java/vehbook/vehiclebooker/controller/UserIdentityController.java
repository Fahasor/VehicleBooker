package vehbook.vehiclebooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vehbook.vehiclebooker.model.UserIdentity;
import vehbook.vehiclebooker.service.UserIdentityService;

@RestController
public class UserIdentityController {
    private final UserIdentityService userIdentityService;

    @Autowired
    public UserIdentityController (UserIdentityService service){
        this.userIdentityService = service;
    }

    @GetMapping("/users")
    public UserIdentity getIdentityUser(@RequestParam String id) {
        return userIdentityService.getUserIdentity(id);
    }
}
