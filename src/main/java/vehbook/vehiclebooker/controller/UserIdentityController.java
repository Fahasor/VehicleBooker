package vehbook.vehiclebooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vehbook.vehiclebooker.model.UserIdentity;
import vehbook.vehiclebooker.service.UserIdentityService;

import java.math.BigInteger;

@RestController
public class UserIdentityController {
    private final UserIdentityService userIdentityService;

    @Autowired
    public UserIdentityController (UserIdentityService service){
        this.userIdentityService = service;
    }

    @GetMapping("/users")
    public UserIdentity getIdentityUser(@RequestParam BigInteger id) {
        return userIdentityService.getUserIdentity(id);
    }

    @PostMapping("/users")
    public UserIdentity createIdentityUser(@RequestBody UserIdentity userIdentity) {
        return userIdentityService.createUserIdentity(userIdentity);
    }
}
